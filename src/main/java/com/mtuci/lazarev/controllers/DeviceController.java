package com.mtuci.lazarev.controllers;

import com.mtuci.lazarev.models.Device;
import com.mtuci.lazarev.requests.DataDeviceRequest;
import com.mtuci.lazarev.service.impl.DeviceServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manage/device")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class DeviceController {
    private final DeviceServiceImpl deviceService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody DataDeviceRequest deviceRequest) {
        try {
            Device device = deviceService.save(deviceRequest);
            deviceRequest.setId(device.getId());
            return ResponseEntity.ok(deviceRequest);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            List<Device> devices = deviceService.getAll();
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

    @PutMapping
    public ResponseEntity<?> update(@RequestBody DataDeviceRequest deviceRequest) {
        try {
            deviceService.update(deviceRequest);
            return ResponseEntity.ok(deviceRequest);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam Long id) {
        try {
            deviceService.delete(id);
            return ResponseEntity.ok("Устройство удалено");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
