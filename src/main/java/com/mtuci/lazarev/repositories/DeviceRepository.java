package com.mtuci.lazarev.repositories;

import com.mtuci.lazarev.models.ApplicationUser;
import com.mtuci.lazarev.models.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface DeviceRepository extends JpaRepository<Device, Long> {
    Optional<Device> findByNameAndMacAddressAndUser(String name, String mac_address, ApplicationUser user);
    Optional<Device> findByMacAddress(String mac_address);
    List<Device> findAllByUser(ApplicationUser user);
}
