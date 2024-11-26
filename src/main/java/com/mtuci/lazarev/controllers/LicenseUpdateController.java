package com.mtuci.lazarev.controllers;

import com.mtuci.lazarev.configuration.JwtTokenProvider;
import com.mtuci.lazarev.exceptions.categories.AuthenticationErrorException;
import com.mtuci.lazarev.exceptions.categories.UserNotFoundException;
import com.mtuci.lazarev.models.ApplicationUser;
import com.mtuci.lazarev.models.Ticket;
import com.mtuci.lazarev.requests.LicenseUpdateRequest;
import com.mtuci.lazarev.service.impl.AuthenticationServiceImpl;
import com.mtuci.lazarev.service.impl.LicenseServiceImpl;
import com.mtuci.lazarev.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/licenseUpdate")
@RequiredArgsConstructor
public class LicenseUpdateController {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserServiceImpl userService;
    private final AuthenticationServiceImpl authenticationService;
    private final LicenseServiceImpl licenseService;

    @PostMapping
    public ResponseEntity<?> licenseUpdate(@RequestHeader("Authorization") String auth, @RequestBody LicenseUpdateRequest licenseUpdateRequest) {
        try {
            // Получить аутентифицированного пользователя
            String login = jwtTokenProvider.getUsername(auth.split(" ")[1]);
            ApplicationUser user = userService.getUserByLogin(login).orElseThrow(
                    () -> new UserNotFoundException("Пользователь не найден")
            );

            // Аунтентификация пользователя
            if (!authenticationService.authenticate(user, licenseUpdateRequest.getPassword()))
                throw new AuthenticationErrorException("Аутентификация не удалась");

            // запрос на продление
            List<Ticket> tickets = licenseService.licenseRenewal(licenseUpdateRequest.getCodeActivation(), user);

            return ResponseEntity.ok(tickets);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(String.format("Ошибка(%s)", e.getMessage()));
        }
    }
}