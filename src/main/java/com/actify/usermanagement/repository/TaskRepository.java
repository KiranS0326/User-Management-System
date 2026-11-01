package com.actify.usermanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.actify.usermanagement.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

	List<Task> findByUserId(Long userId);
}
