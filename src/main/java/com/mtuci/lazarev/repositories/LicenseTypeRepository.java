package com.mtuci.lazarev.repositories;

import com.mtuci.lazarev.models.License;
import com.mtuci.lazarev.models.LicenseType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LicenseTypeRepository extends JpaRepository<LicenseType, Long> {
}
