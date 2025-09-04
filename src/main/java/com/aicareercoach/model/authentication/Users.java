package com.aicareercoach.model;


import org.springframework.stereotype.Component;

@Component

public class Users {
    private String username;
    private String password;
    private String email;
    private String role; // e.g., "user", "admin"
}
