package com.mtuci.lazarev.service;

import com.mtuci.lazarev.models.ApplicationUser;
import com.mtuci.lazarev.models.License;
import com.mtuci.lazarev.models.LicenseHistory;
import com.mtuci.lazarev.requests.DataLicenseHistoryRequest;

import java.util.List;
import java.util.Optional;

public interface LicenseHistoryService {
    boolean recordLicenseChange(
            License license, ApplicationUser owner,
            String status, String description);
    Optional<LicenseHistory> findById(Long id);

    // save
    LicenseHistory save(DataLicenseHistoryRequest request);

    // read
    List<LicenseHistory> getAll();

    // update
    LicenseHistory update(DataLicenseHistoryRequest request);

    // delete
    void delete(Long id);
}
