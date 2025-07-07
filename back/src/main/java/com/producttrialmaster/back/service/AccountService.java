package com.producttrialmaster.back.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.producttrialmaster.back.dto.AccountCreationDTO;
import com.producttrialmaster.back.exception.ApiException;
import com.producttrialmaster.back.model.Account;
import com.producttrialmaster.back.repository.AccountRepository;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public AccountService(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void createAccount(AccountCreationDTO dto) {
        if (accountRepository.existsByEmail(dto.getEmail())) {
            throw new ApiException("Un compte avec cet email existe déjà.",HttpStatus.CONFLICT);
        }

        Account account = new Account();
        account.setUsername(dto.getUsername());
        account.setFirstname(dto.getFirstname());
        account.setEmail(dto.getEmail());
        account.setPassword(passwordEncoder.encode(dto.getPassword()));

        accountRepository.save(account);
    }
}
