package com.producttrialmaster.back.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.producttrialmaster.back.exception.ApiException;
import com.producttrialmaster.back.model.Account;
import com.producttrialmaster.back.repository.AccountRepository;
import com.producttrialmaster.back.security.JwtTokenUtil;

@Service
public class TokenService {
    
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    public TokenService(AccountRepository accountRepository, PasswordEncoder passwordEncoder, JwtTokenUtil jwtTokenUtil){
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public String login(String email, String password){
        Account account = accountRepository.findByEmail(email)
            .orElseThrow(() -> new ApiException("Utilisateur non trouvé", HttpStatus.NOT_FOUND));

        if(!passwordEncoder.matches(password, account.getPassword())){
            throw new ApiException("Mot de passe incorect", HttpStatus.UNAUTHORIZED);
        }

        return jwtTokenUtil.generateToken(account.getEmail());
    }
}
