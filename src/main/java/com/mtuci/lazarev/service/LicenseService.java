package com.mtuci.lazarev.service;

import com.mtuci.lazarev.models.ApplicationUser;
import com.mtuci.lazarev.models.Device;
import com.mtuci.lazarev.models.License;
import com.mtuci.lazarev.models.Ticket;

import java.util.Optional;

public interface LicenseService {
    License createLicense(
            Long productId, Long ownerId, Long licenseTypeId,
            Integer device_count, Integer duration
            );

    Ticket activateLicense(String activationCode, Device device, ApplicationUser user);

    boolean validateLicense(License license, Device device, ApplicationUser user);
    void createDeviceLicense(License license, Device device);
    void updateLicense(License license);
    Ticket generateTicket(License license, Device device);

    Optional<License> findById(Long id);
}
