package com.mtuci.lazarev.controllers;

import com.mtuci.lazarev.models.LicenseHistory;
import com.mtuci.lazarev.requests.DataLicenseHistoryRequest;
import com.mtuci.lazarev.service.impl.LicenseHistoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manage/licenseHistory")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class LicenseHistoryController {
    private final LicenseHistoryServiceImpl licenseHistoryService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody DataLicenseHistoryRequest request) {
        try {
            LicenseHistory license = licenseHistoryService.save(request);
            request.setId(license.getId());
            return ResponseEntity.ok(request);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            List<LicenseHistory> licenseHistories = licenseHistoryService.getAll();
            List<DataLicenseHistoryRequest> data = licenseHistories.stream().map(
                    licenseHistory -> new DataLicenseHistoryRequest(
                            licenseHistory.getId(),
                            licenseHistory.getLicense().getId(),
                            licenseHistory.getUser().getId(),
                            licenseHistory.getStatus(),
                            licenseHistory.getDescription(),
                            licenseHistory.getChange_date()
                    )
            ).toList();
            return ResponseEntity.ok(data);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody DataLicenseHistoryRequest request) {
        try {
            licenseHistoryService.update(request);
            return ResponseEntity.ok(request);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam Long id) {
        try {
            licenseHistoryService.delete(id);
            return ResponseEntity.ok("Удалена история лицензии");
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
