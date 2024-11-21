package com.mtuci.lazarev.repositories;

import com.mtuci.lazarev.models.LicenseHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LicenseHistoryRepository extends JpaRepository<LicenseHistory, Long> {
}
