package com.mtuci.lazarev.controllers;

import com.mtuci.lazarev.configuration.JwtTokenProvider;
import com.mtuci.lazarev.exceptions.categories.UserNotFoundException;
import com.mtuci.lazarev.models.ApplicationUser;
import com.mtuci.lazarev.models.Device;
import com.mtuci.lazarev.models.Ticket;
import com.mtuci.lazarev.requests.DeviceRequest;
import com.mtuci.lazarev.service.impl.DeviceServiceImpl;
import com.mtuci.lazarev.service.impl.LicenseServiceImpl;
import com.mtuci.lazarev.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/activation")
@RequiredArgsConstructor
public class ActivationController {
    private final UserServiceImpl userService;
    private final DeviceServiceImpl deviceService;
    private final JwtTokenProvider jwtTokenProvider;
    private final LicenseServiceImpl licenseService;

    @PostMapping
    public ResponseEntity<?> activateLicense(@RequestHeader("Authorization") String auth, @RequestBody DeviceRequest deviceRequest) {
        try {
            // Получить аутентифицированного пользователя
            String login = jwtTokenProvider.getUsername(auth.split(" ")[1]);
            ApplicationUser user = userService.getUserByLogin(login).orElseThrow(
                    () -> new UserNotFoundException("Пользователь не найден")
            );

            // Получить устройство
            Device device = deviceService.registerOrUpdateDevice(deviceRequest.getName(), deviceRequest.getMacAddress(), user);

            Ticket ticket = licenseService.activateLicense(deviceRequest.getActivationCode(), device, user);

            return ResponseEntity.ok(ticket);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(String.format("Ошибка(%s)", e.getMessage()));
        }
    }
}
