package com.JWTexample.JWT.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/hello")
    public String hello() {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	System.out.println(authentication.getName());
    	System.out.println(authentication.getAuthorities());
    	System.out.println(authentication.isAuthenticated());
        return "Hello, secured world!";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String admin() {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	System.out.println(authentication.getName());
    	System.out.println(authentication.getAuthorities());
    	System.out.println(authentication.isAuthenticated());
        return "Admin only";
    }
}

