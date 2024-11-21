package com.mtuci.lazarev.repositories;

import com.mtuci.lazarev.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}