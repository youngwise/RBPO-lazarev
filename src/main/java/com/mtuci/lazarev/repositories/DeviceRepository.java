package com.mtuci.lazarev.repositories;

import com.mtuci.lazarev.models.ActivationCode;
import com.mtuci.lazarev.models.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Long> {
}
