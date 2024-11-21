package com.mtuci.lazarev.service;

import com.mtuci.lazarev.models.ApplicationUser;
import com.mtuci.lazarev.models.License;
import com.mtuci.lazarev.models.LicenseHistory;

import java.util.Optional;

public interface LicenseHistoryService {
    boolean recordLicenseChange(
            License license, ApplicationUser owner,
            String status, String description);
    Optional<LicenseHistory> findById(Long id);
}
