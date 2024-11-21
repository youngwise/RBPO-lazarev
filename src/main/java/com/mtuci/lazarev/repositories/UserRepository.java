package com.mtuci.lazarev.repositories;

import com.mtuci.lazarev.models.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<ApplicationUser, Long> {
    Optional<ApplicationUser> findByLogin(String login);
}
