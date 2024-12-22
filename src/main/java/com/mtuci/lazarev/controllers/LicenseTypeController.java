package com.mtuci.lazarev.controllers;

import com.mtuci.lazarev.models.LicenseType;
import com.mtuci.lazarev.requests.DataLicenseTypeRequest;
import com.mtuci.lazarev.service.impl.LicenseTypeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manage/licenseType")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class LicenseTypeController {
    private final LicenseTypeServiceImpl licenseTypeService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody DataLicenseTypeRequest request) {
        try {
            LicenseType license = licenseTypeService.save(request);
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
            List<LicenseType> licenses = licenseTypeService.getAll();
            List<DataLicenseTypeRequest> data = licenses.stream().map(
                    licenseType -> new DataLicenseTypeRequest(
                            licenseType.getId(),
                            licenseType.getName(),
                            licenseType.getDescription(),
                            licenseType.getDefault_duration()
                    )
            ).toList();
            return ResponseEntity.ok(data);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody DataLicenseTypeRequest request) {
        try {
            licenseTypeService.update(request);
            return ResponseEntity.ok(request);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam Long id) {
        try {
            licenseTypeService.delete(id);
            return ResponseEntity.ok("Тип лицензии удалён");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
