package com.mtuci.lazarev.controllers;

import com.mtuci.lazarev.models.ApplicationUser;
import com.mtuci.lazarev.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {
    private final UserServiceImpl userDetailsService;

    @PostMapping
    public ResponseEntity<?> registration(
            @RequestParam String login,
            @RequestParam String email,
            @RequestParam String password
    ) {
        ApplicationUser applicationUser = new ApplicationUser();
        applicationUser.setLogin(login);
        applicationUser.setEmail(email);

        if (!userDetailsService.saveUser(applicationUser, password))
            return ResponseEntity.ok("Пользователь уже существует!");

        return ResponseEntity.ok("Регистрация прошла успешно!");
    }
}
