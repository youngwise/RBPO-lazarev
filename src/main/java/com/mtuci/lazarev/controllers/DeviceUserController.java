package com.mtuci.lazarev.controllers;

import com.mtuci.lazarev.configuration.JwtTokenProvider;
import com.mtuci.lazarev.exceptions.categories.UserNotFoundException;
import com.mtuci.lazarev.models.ApplicationUser;
import com.mtuci.lazarev.models.Device;
import com.mtuci.lazarev.requests.DataDeviceRequest;
import com.mtuci.lazarev.service.impl.DeviceServiceImpl;
import com.mtuci.lazarev.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user_devices")
public class DeviceUserController {
    private final DeviceServiceImpl deviceService;
    private final UserServiceImpl userService;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping
    public ResponseEntity<?> getAllByUser(@RequestHeader("Authorization") String auth) {
        try {
            String login = jwtTokenProvider.getUsername(auth.split(" ")[1]);
            ApplicationUser user = userService.getUserByLogin(login).orElseThrow(
                    () -> new UserNotFoundException("User not found")
            );

            List<Device> devices = deviceService.getAllByUser(user);
            List<DataDeviceRequest> dataDevices = devices.stream().map(
                    device -> new DataDeviceRequest(
                            device.getId(),
                            device.getUser().getId(),
                            device.getName(),
                            device.getMacAddress()
                    )
            ).toList();
            return ResponseEntity.ok(dataDevices);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
