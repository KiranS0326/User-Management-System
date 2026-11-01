package com.actify.usermanagement.service;

import java.util.List;

import com.actify.usermanagement.dto.AssignRoleRequest;
import com.actify.usermanagement.dto.UserDto;

public interface UserService {

	List<UserDto> getAllUsers();

	UserDto getUserById(Long id);

	UserDto getUserByEmail(String email);

	UserDto updateUser(Long id, UserDto userDto);

	void deleteUser(Long id);

	UserDto assignRoles(Long userId, AssignRoleRequest request);

}
