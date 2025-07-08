package com.producttrialmaster.back.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.producttrialmaster.back.dto.LoginRequestDTO;
import com.producttrialmaster.back.service.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/token")
public class TokenController {
    
     private final TokenService tokenService;

    public TokenController(TokenService tokenService){
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<Map<String,String>> login(@Valid@RequestBody LoginRequestDTO loginDTO) {
        String token = tokenService.login(loginDTO.getEmail(),loginDTO.getPassword());
        return ResponseEntity.ok(Map.of("token", token));
    }
}