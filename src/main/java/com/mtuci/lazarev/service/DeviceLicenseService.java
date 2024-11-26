package com.mtuci.lazarev.service;

import com.mtuci.lazarev.models.DeviceLicense;
import com.mtuci.lazarev.requests.DataDeviceLicenseRequest;

import java.util.List;

public interface DeviceLicenseService {
    DeviceLicense saveDeviceLicense(DeviceLicense deviceLicense);

    // save
    DeviceLicense save(DataDeviceLicenseRequest request);

    // read
    List<DeviceLicense> getAll();

    // update
    DeviceLicense update(DataDeviceLicenseRequest request);

    // delete
    void delete(Long id);
}
