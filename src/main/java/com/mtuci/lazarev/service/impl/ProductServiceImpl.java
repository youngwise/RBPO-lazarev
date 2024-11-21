package com.mtuci.lazarev.service.impl;

import com.mtuci.lazarev.models.Product;
import com.mtuci.lazarev.repositories.ProductRepository;
import com.mtuci.lazarev.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }
}
