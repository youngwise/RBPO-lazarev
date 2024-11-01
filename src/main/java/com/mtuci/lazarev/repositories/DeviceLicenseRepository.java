package com.mtuci.lazarev.repositories;

import com.mtuci.lazarev.models.Device;
import com.mtuci.lazarev.models.DeviceLicense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceLicenseRepository extends JpaRepository<DeviceLicense, Long> {
}
