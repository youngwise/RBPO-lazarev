package com.mtuci.lazarev.repositories;

import com.mtuci.lazarev.models.Role;
import com.mtuci.lazarev.models.Trial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
