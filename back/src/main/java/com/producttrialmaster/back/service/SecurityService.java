package com.producttrialmaster.back.service;

import org.springframework.stereotype.Service;

@Service("securityService")
public class SecurityService {

    public boolean isAdmin(String email) {
        return "admin@admin.com".equalsIgnoreCase(email);
    }
}
