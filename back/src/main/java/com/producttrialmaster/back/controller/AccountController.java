package com.producttrialmaster.back.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.producttrialmaster.back.dto.AccountCreationDTO;
import com.producttrialmaster.back.service.AccountService;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<Void> createAccount(@RequestBody AccountCreationDTO accountDTO) {
        accountService.createAccount(accountDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
