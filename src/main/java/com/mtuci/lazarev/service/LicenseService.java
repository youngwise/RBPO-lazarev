package com.mtuci.lazarev.service;

import com.mtuci.lazarev.models.License;
import java.util.Optional;

public interface LicenseService {
    License createLicense(
            Long productId, Long ownerId, Long licenseTypeId,
            Integer device_count, Integer duration
            );

    Optional<License> findById(Long id);
}
