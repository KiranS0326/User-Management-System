package com.actify.usermanagement.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class AssignRoleRequest {
    private Set<String> roles; 
}