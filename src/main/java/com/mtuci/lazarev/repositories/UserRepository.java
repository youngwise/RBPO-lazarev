package com.mtuci.lazarev.repositories;

import com.mtuci.lazarev.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
