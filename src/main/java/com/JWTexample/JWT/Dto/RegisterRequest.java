package com.JWTexample.JWT.Dto;

import lombok.Data;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private String role;
}
