package com.mtuci.lazarev.controllers;

import com.mtuci.lazarev.models.License;
import com.mtuci.lazarev.requests.LicenseCreateRequest;
import com.mtuci.lazarev.service.impl.LicenseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/licenseCreate")
@RequiredArgsConstructor
public class LicenseCreateController {
    private final LicenseServiceImpl licenseService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createLicense(@RequestBody LicenseCreateRequest licenseCreateRequest) {
        try {
            License license = licenseService.createLicense(
                    licenseCreateRequest.getProductId(),
                    licenseCreateRequest.getOwnerId(),
                    licenseCreateRequest.getLicenseTypeId(),
                    licenseCreateRequest.getDevice_count(),
                    licenseCreateRequest.getDuration());

            return ResponseEntity.ok(license);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(String.format("Ошибка(%s)", e.getMessage()));
        }
    }
}
