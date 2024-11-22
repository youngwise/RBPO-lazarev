package com.mtuci.lazarev.service;

import com.mtuci.lazarev.models.ApplicationUser;
import com.mtuci.lazarev.models.Device;
import com.mtuci.lazarev.requests.DeviceInfoRequest;

import java.util.Optional;

public interface DeviceService {
    Device registerOrUpdateDevice(String nameDevice, String macDevice, ApplicationUser user);
    Optional<Device> findDeviceByInfo(String name, String mac_address);
}
