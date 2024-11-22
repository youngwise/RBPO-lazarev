package com.mtuci.lazarev.controllers;

import com.mtuci.lazarev.configuration.JwtTokenProvider;
import com.mtuci.lazarev.exceptions.categories.DeviceNotFoundException;
import com.mtuci.lazarev.exceptions.categories.UserNotFoundException;
import com.mtuci.lazarev.models.ApplicationUser;
import com.mtuci.lazarev.models.Device;
import com.mtuci.lazarev.models.License;
import com.mtuci.lazarev.models.Ticket;
import com.mtuci.lazarev.requests.DeviceInfoRequest;
import com.mtuci.lazarev.service.impl.AuthenticationServiceImpl;
import com.mtuci.lazarev.service.impl.DeviceServiceImpl;
import com.mtuci.lazarev.service.impl.LicenseServiceImpl;
import com.mtuci.lazarev.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/licenseInfo")
@RequiredArgsConstructor
public class LicenseInfoController {
    private final UserServiceImpl userService;
    private final DeviceServiceImpl deviceService;
    private final JwtTokenProvider jwtTokenProvider;
    private final LicenseServiceImpl licenseService;

    @PostMapping
    public ResponseEntity<?> getLicenseInfo(@RequestHeader("Authorization") String auth, @RequestBody DeviceInfoRequest deviceInfoRequest) {
        try {
            // Получить аутентифицированного пользователя
            String login = jwtTokenProvider.getUsername(auth.split(" ")[1]);
            ApplicationUser user = userService.getUserByLogin(login).orElseThrow(
                    () -> new UserNotFoundException("User not found")
            );

            // Получить устройство
            Device device = deviceService.findDeviceByInfo(deviceInfoRequest.getName(), deviceInfoRequest.getMacAddress()).orElseThrow(
                    () -> new DeviceNotFoundException("Устройство не найдено")
            );

            List<License> licenses = licenseService.getActiveLicensesForDevice(device, user);
            List<Ticket> tickets = licenses.stream().map(license -> generateTicket(license, device)).toList();

            return ResponseEntity.ok(tickets);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(String.format("Ошибка(%s)", e.getMessage()));
        }
    }

    private Ticket generateTicket(License license, Device device) {
        return licenseService.generateTicket(license, device);
    }
}
