package com.actify.usermanagement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDto {
    private Long id;
    private String title;
    private String description;
    private String status; 
    private Long userId;   
}