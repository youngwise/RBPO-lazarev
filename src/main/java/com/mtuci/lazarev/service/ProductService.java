package com.mtuci.lazarev.service;


import com.mtuci.lazarev.models.Product;
import com.mtuci.lazarev.requests.DataProductRequest;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<Product> getProductById(Long id);

    // save
    Product save(DataProductRequest request);

    // read
    List<Product> getAll();

    // update
    Product update(DataProductRequest request);

    // delete
    void delete(Long id);
}
