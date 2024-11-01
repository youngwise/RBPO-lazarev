package com.mtuci.lazarev.repositories;

import com.mtuci.lazarev.models.LicenseHistory;
import com.mtuci.lazarev.models.Trial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrialRepository extends JpaRepository<Trial, Long> {
}
