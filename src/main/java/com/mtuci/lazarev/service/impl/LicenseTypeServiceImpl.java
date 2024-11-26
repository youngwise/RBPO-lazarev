package com.mtuci.lazarev.service.impl;

import com.mtuci.lazarev.exceptions.categories.LicenseTypeNotFoundException;
import com.mtuci.lazarev.models.LicenseType;
import com.mtuci.lazarev.repositories.LicenseTypeRepository;
import com.mtuci.lazarev.requests.DataLicenseTypeRequest;
import com.mtuci.lazarev.service.LicenseTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LicenseTypeServiceImpl implements LicenseTypeService {
    private final LicenseTypeRepository licenseTypeRepository;

    @Override
    public Optional<LicenseType> getLicenseTypeById(Long id) {
        return licenseTypeRepository.findById(id);
    }

    private LicenseType edit(LicenseType licenseType, DataLicenseTypeRequest request) {
        licenseType.setName(request.getName());
        licenseType.setDescription(request.getDescription());
        licenseType.setDefault_duration(request.getDefault_duration());
        return licenseType;
    }

    @Override
    public LicenseType save(DataLicenseTypeRequest request) {
        return licenseTypeRepository.save(edit(new LicenseType(), request));
    }

    @Override
    public List<LicenseType> getAll() {
        return licenseTypeRepository.findAll();
    }

    @Override
    public LicenseType update(DataLicenseTypeRequest request) {
        LicenseType licenseType = licenseTypeRepository.findById(request.getId()).orElseThrow(
                () -> new LicenseTypeNotFoundException("Тип лицензии не найден")
        );
        return licenseTypeRepository.save(edit(licenseType, request));
    }

    @Override
    public void delete(Long id) {
        licenseTypeRepository.deleteById(id);
    }
}
