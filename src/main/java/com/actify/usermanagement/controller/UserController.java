package com.actify.usermanagement.controller;

import com.actify.usermanagement.dto.TaskDto;
import com.actify.usermanagement.dto.UserDto;
import com.actify.usermanagement.service.TaskService;
import com.actify.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@PreAuthorize("isAuthenticated()")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    @GetMapping("/profile")
    public ResponseEntity<UserDto> getMyProfile(Authentication authentication) {
        String email = authentication.getName();
        UserDto userDto = userService.getUserByEmail(email);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskDto>> getMyTasks(Authentication authentication) {
        String email = authentication.getName();
        UserDto userDto = userService.getUserByEmail(email);
        List<TaskDto> tasks = taskService.getTasksByUserId(userDto.getId());
        return ResponseEntity.ok(tasks);
    }

    @PutMapping("/tasks/{taskId}/status")
    public ResponseEntity<TaskDto> updateMyTaskStatus(@PathVariable Long taskId, @RequestParam String status) {
        return ResponseEntity.ok(taskService.updateTaskStatus(taskId, status));
    }
}