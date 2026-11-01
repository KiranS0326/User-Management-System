package com.actify.usermanagement.bootstrap;

import com.actify.usermanagement.entity.Role;
import com.actify.usermanagement.entity.User;
import com.actify.usermanagement.repository.RoleRepository;
import com.actify.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Create Roles if not exist
        Role adminRole = roleRepository.findByName("Admin").orElseGet(() -> {
            Role r = new Role();
            r.setName("Admin");
            return roleRepository.save(r);
        });

        Role managerRole = roleRepository.findByName("Manager").orElseGet(() -> {
            Role r = new Role();
            r.setName("Manager");
            return roleRepository.save(r);
        });

        Role userRole = roleRepository.findByName("User").orElseGet(() -> {
            Role r = new Role();
            r.setName("User");
            return roleRepository.save(r);
        });

        // Create sample Admin user
        if (!userRepository.existsByEmail("admin@actify.com")) {
            User admin = new User();
            admin.setName("System Admin");
            admin.setEmail("admin@actify.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRoles(Set.of(adminRole));
            userRepository.save(admin);
        }

        // Create sample Manager user
        if (!userRepository.existsByEmail("manager@actify.com")) {
            User manager = new User();
            manager.setName("Project Manager");
            manager.setEmail("manager@actify.com");
            manager.setPassword(passwordEncoder.encode("manager123"));
            manager.setRoles(Set.of(managerRole));
            userRepository.save(manager);
        }

        // Create sample Regular user
        if (!userRepository.existsByEmail("user@actify.com")) {
            User regularUser = new User();
            regularUser.setName("John User");
            regularUser.setEmail("user@actify.com");
            regularUser.setPassword(passwordEncoder.encode("user123"));
            regularUser.setRoles(Set.of(userRole));
            userRepository.save(regularUser);
        }
    }
}