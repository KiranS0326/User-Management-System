package com.actify.usermanagement.controller;

import com.actify.usermanagement.dto.TaskDto;
import com.actify.usermanagement.dto.UserDto;
import com.actify.usermanagement.service.TaskService;
import com.actify.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/manager")
@PreAuthorize("hasRole('Manager')")
public class ManagerController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsersWithTasks() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/tasks")
    public ResponseEntity<TaskDto> assignTask(@RequestBody TaskDto taskDto) {
        return ResponseEntity.ok(taskService.assignTask(taskDto));
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }
}