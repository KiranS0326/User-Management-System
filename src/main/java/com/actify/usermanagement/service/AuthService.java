package com.actify.usermanagement.service;

import com.actify.usermanagement.dto.JwtResponse;
import com.actify.usermanagement.dto.LoginRequest;
import com.actify.usermanagement.dto.SignupRequest;
import com.actify.usermanagement.dto.UserDto;

public interface AuthService {

	UserDto registerUser(SignupRequest signupRequest);

	JwtResponse authenticateUser(LoginRequest loginRequest);
}