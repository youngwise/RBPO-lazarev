package com.mtuci.lazarev.repositories;

import com.mtuci.lazarev.models.ActivationCode;
import com.mtuci.lazarev.models.Trial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivationCodeRepository extends JpaRepository<ActivationCode, Long> {
}
