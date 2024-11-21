package com.mtuci.lazarev.service;

import com.mtuci.lazarev.models.LicenseType;

import java.util.Optional;

public interface LicenseTypeService {
    Optional<LicenseType> getLicenseById(Long id);
}
