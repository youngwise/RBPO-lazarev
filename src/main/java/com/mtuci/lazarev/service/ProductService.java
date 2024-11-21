package com.mtuci.lazarev.service;


import com.mtuci.lazarev.models.Product;

import java.util.Optional;

public interface ProductService {
    Optional<Product> getProductById(Long id);
}
