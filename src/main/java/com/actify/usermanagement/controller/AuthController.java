package com.actify.usermanagement.controller;

import com.actify.usermanagement.dto.JwtResponse;
import com.actify.usermanagement.dto.LoginRequest;
import com.actify.usermanagement.dto.SignupRequest;
import com.actify.usermanagement.dto.UserDto;
import com.actify.usermanagement.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        UserDto registeredUser = authService.registerUser(signupRequest);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        JwtResponse jwtResponse = authService.authenticateUser(loginRequest);
        return ResponseEntity.ok(jwtResponse);
    }
}