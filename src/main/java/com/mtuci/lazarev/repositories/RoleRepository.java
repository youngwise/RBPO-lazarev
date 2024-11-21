package com.mtuci.lazarev.repositories;

import com.mtuci.lazarev.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
