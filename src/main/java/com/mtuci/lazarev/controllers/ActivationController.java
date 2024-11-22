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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.Format;
import java.text.SimpleDateFormat;

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
                    () -> new UserNotFoundException("User not found")
            );

            // Получить устройство
            Device device = deviceService.registerOrUpdateDevice(deviceRequest.getName(), deviceRequest.getMacAddress(), user);

            Ticket ticket = licenseService.activateLicense(deviceRequest.getActivationCode(), device, user);

//            Format formatter = new SimpleDateFormat("dd.MM.yyyy");
//            String answer = "Билет активации лицензии:\n" +
//                    String.format("\tТекущая дата: %s\n", formatter.format(ticket.getNowDate())) +
//                    String.format("\tДата активации: %s\n", formatter.format(ticket.getActivationDate())) +
//                    String.format("\tДата окончания: %s\n", formatter.format(ticket.getExpirationDate())) +
//                    String.format("\tСрок действия билета: %d с\n", ticket.getExpiration()) +
//                    String.format("\tID пользователя: %d\n", ticket.getUserID()) +
//                    String.format("\tID устройства: %d\n", ticket.getDeviceID()) +
//                    String.format("\tСостояние лицензии: %s\n", ticket.isBlockedLicence() ? "заблокирована" : "активна") +
//                    String.format("\tПодпись: %s\n", ticket.getDigitalSignature());

            return ResponseEntity.ok(ticket);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(String.format("Ошибка(%s)", e.getMessage()));
        }
    }
}
