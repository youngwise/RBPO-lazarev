package com.mtuci.lazarev.service.impl;

import com.mtuci.lazarev.models.LicenseType;
import com.mtuci.lazarev.repositories.LicenseTypeRepository;
import com.mtuci.lazarev.service.LicenseTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LicenseTypeServiceImpl implements LicenseTypeService {
    private final LicenseTypeRepository licenseTypeRepository;

    @Override
    public Optional<LicenseType> getLicenseById(Long id) {
        return licenseTypeRepository.findById(id);
    }
}
