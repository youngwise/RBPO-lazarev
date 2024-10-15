package com.mtuci.lazarev.repositories;

import com.mtuci.lazarev.models.Main;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MainRepository extends JpaRepository<Main, Long> {
    Main findByName(String name);
}
