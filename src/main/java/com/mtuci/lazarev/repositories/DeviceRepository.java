package com.mtuci.lazarev.repositories;

import com.mtuci.lazarev.models.ApplicationUser;
import com.mtuci.lazarev.models.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface DeviceRepository extends JpaRepository<Device, Long> {
    Optional<Device> findByUser(ApplicationUser user);
    Optional<Device> findByNameAndMacAddress(String name, String mac_address);
}
