package com.mtuci.lazarev.repositories;

import com.mtuci.lazarev.models.Device;
import com.mtuci.lazarev.models.DeviceLicense;
import com.mtuci.lazarev.models.License;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeviceLicenseRepository extends JpaRepository<DeviceLicense, Long> {
    Optional<DeviceLicense> findByDeviceAndLicense(Device device, License license);
}
