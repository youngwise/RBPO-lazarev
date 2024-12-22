package com.mtuci.lazarev.controllers;

import com.mtuci.lazarev.configuration.JwtTokenProvider;
import com.mtuci.lazarev.models.ApplicationRole;
import com.mtuci.lazarev.models.ApplicationUser;
import com.mtuci.lazarev.models.AuthenticationResponse;
import com.mtuci.lazarev.repositories.UserRepository;
import com.mtuci.lazarev.requests.AuthenticationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/login")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

/*
    Формат ввода: json
    {
        "login": "login",
        "password": "password"
    }
*/

    @PostMapping
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest request) {
        try {
            String login = request.getLogin();

            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(login, request.getPassword())
            );

            ApplicationUser user = userRepository.findByLogin(login).orElseThrow(() -> new UsernameNotFoundException("Пользоваетель не найден!"));

            String token = jwtTokenProvider.createToken(login, user.getRole().getGrantedAuthorities());

            return ResponseEntity.ok(new AuthenticationResponse(token, login, user.getRole().equals(ApplicationRole.ADMIN)));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Неправильный логин или пароль!");
        }
    }
}
