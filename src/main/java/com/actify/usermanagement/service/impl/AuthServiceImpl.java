package com.actify.usermanagement.service.impl;

import com.actify.usermanagement.config.JwtUtils;
import com.actify.usermanagement.dto.JwtResponse;
import com.actify.usermanagement.dto.LoginRequest;
import com.actify.usermanagement.dto.SignupRequest;
import com.actify.usermanagement.dto.UserDto;
import com.actify.usermanagement.entity.Role;
import com.actify.usermanagement.entity.User;
import com.actify.usermanagement.exception.DuplicateUserException;
import com.actify.usermanagement.exception.ResourceNotFoundException;
import com.actify.usermanagement.repository.RoleRepository;
import com.actify.usermanagement.repository.UserRepository;
import com.actify.usermanagement.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public UserDto registerUser(SignupRequest signupRequest) {
        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new DuplicateUserException("Error: Email is already in use!");
        }

        User user = new User();
        user.setName(signupRequest.getName());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword())); // BCrypt encoding

        Set<String> strRoles = signupRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null || strRoles.isEmpty()) {
            Role userRole = roleRepository.findByName("User")
                    .orElseThrow(() -> new ResourceNotFoundException("Error: Default Role 'User' not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(roleName -> {
                Role role = roleRepository.findByName(roleName)
                        .orElseThrow(() -> new ResourceNotFoundException("Error: Role not found: " + roleName));
                roles.add(role);
            });
        }

        user.setRoles(roles);
        User savedUser = userRepository.save(user);

        UserDto dto = new UserDto();
        dto.setId(savedUser.getId());
        dto.setName(savedUser.getName());
        dto.setEmail(savedUser.getEmail());
        dto.setRoles(savedUser.getRoles().stream().map(Role::getName).collect(Collectors.toSet()));
        return dto;
    }

    @Override
    public JwtResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        org.springframework.security.core.userdetails.UserDetails userDetails = 
                (org.springframework.security.core.userdetails.UserDetails) authentication.getPrincipal();
        
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return new JwtResponse(jwt, user.getId(), user.getEmail(), roles);
    }
}