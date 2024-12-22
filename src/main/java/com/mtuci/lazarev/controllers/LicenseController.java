package com.mtuci.lazarev.controllers;

import com.mtuci.lazarev.models.License;
import com.mtuci.lazarev.requests.DataLicenseRequest;
import com.mtuci.lazarev.service.impl.LicenseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manage/license")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class LicenseController {
    private final LicenseServiceImpl licenseService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody DataLicenseRequest request) {
        try {
            License license = licenseService.save(request);
            request.setId(license.getId());
            return ResponseEntity.ok(request);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            List<License> licenses = licenseService.getAll();
            List<DataLicenseRequest> data = licenses.stream().map(
                    license -> new DataLicenseRequest(
                            license.getId(),
                            license.getLicenseType().getId(),
                            license.getProduct().getId(),
                            license.getUser() != null ? license.getUser().getId() : null,
                            license.getOwner().getId(),
                            license.getFirst_activation_date(),
                            license.getEnding_date(),
                            license.isBlocked(),
                            license.getDevice_count(),
                            license.getDuration(),
                            license.getCode(),
                            license.getDescription()
                    )
            ).toList();
            return ResponseEntity.ok(data);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody DataLicenseRequest request) {
        try {
            licenseService.update(request);
            return ResponseEntity.ok(request);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam Long id) {
        try {
            licenseService.delete(id);
            return ResponseEntity.ok("Лицензия удалена");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
