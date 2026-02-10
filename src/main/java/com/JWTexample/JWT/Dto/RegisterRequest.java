package com.JWTexample.JWT.Dto;

import com.JWTexample.JWT.Entity.Role;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private Role role;
}
