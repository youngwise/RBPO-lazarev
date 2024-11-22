package com.mtuci.lazarev.service.impl;

import com.mtuci.lazarev.configuration.SecurityConfig;
import com.mtuci.lazarev.models.ApplicationUser;
import com.mtuci.lazarev.service.AutheneticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AutheneticationService {
    private final SecurityConfig securityConfig;

    @Override
    public boolean authenticate(ApplicationUser user, String password) {
        return user.getPassword_hash().equals(securityConfig.passwordEncoder().encode(password));
    }
}
