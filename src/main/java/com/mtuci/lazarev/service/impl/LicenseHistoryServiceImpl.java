package com.mtuci.lazarev.service.impl;

import com.mtuci.lazarev.models.ApplicationUser;
import com.mtuci.lazarev.models.License;
import com.mtuci.lazarev.models.LicenseHistory;
import com.mtuci.lazarev.repositories.LicenseHistoryRepository;
import com.mtuci.lazarev.repositories.LicenseRepository;
import com.mtuci.lazarev.repositories.UserRepository;
import com.mtuci.lazarev.service.LicenseHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LicenseHistoryServiceImpl implements LicenseHistoryService {
    private final LicenseHistoryRepository licenseHistoryRepository;
    private final UserRepository userRepository;
    private final LicenseRepository licenseRepository;

    @Override
    public boolean recordLicenseChange(License license, ApplicationUser owner, String status, String description) {
        LicenseHistory licenseHistory = new LicenseHistory();
        licenseHistory.setLicense(license);
        licenseHistory.setChange_date(new Date(System.currentTimeMillis()));
        licenseHistory.setUser(owner);
        licenseHistory.setStatus(status);
        licenseHistory.setDescription(description);

        licenseHistoryRepository.save(licenseHistory);
        return true;
    }

    @Override
    public Optional<LicenseHistory> findById(Long id) {
        return licenseHistoryRepository.findById(id);
    }
}
