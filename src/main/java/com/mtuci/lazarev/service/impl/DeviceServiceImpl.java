package com.mtuci.lazarev.service.impl;

import com.mtuci.lazarev.models.ApplicationUser;
import com.mtuci.lazarev.models.Device;
import com.mtuci.lazarev.repositories.DeviceRepository;
import com.mtuci.lazarev.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService {
    private final DeviceRepository deviceRepository;

    private Device createDevice(String nameDevice, String macDevice, ApplicationUser user) {
        Device device = new Device();
        device.setName(nameDevice);
        device.setMac_address(macDevice);
        device.setUser(user);
        return deviceRepository.save(device);
    }

    @Override
    public Device registerOrUpdateDevice(String nameDevice, String macDevice, ApplicationUser user) {
        Device device = deviceRepository.findByUser(user).orElse(null);

        // Новый пользователь и/или новое устройство у пользователя
        if (device == null || !macDevice.equals(device.getMac_address())) {
            device = new Device();
            device.setName(nameDevice);
            device.setMac_address(macDevice);
            device.setUser(user);
        }
        else if (!nameDevice.equals(device.getName())) {    // поменялось название устройства
            device.setName(nameDevice);
        }

        return deviceRepository.save(device);
    }
}
