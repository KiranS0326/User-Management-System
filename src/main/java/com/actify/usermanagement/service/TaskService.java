package com.actify.usermanagement.service;

import com.actify.usermanagement.dto.TaskDto;

import java.util.List;

public interface TaskService {

	TaskDto assignTask(TaskDto taskDto);

	List<TaskDto> getAllTasks();

	List<TaskDto> getTasksByUserId(Long userId);

	TaskDto updateTaskStatus(Long taskId, String status);

	void deleteTask(Long taskId);
}