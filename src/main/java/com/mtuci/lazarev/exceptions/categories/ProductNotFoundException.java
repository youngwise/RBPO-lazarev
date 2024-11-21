package com.mtuci.lazarev.exceptions.categories;

import com.mtuci.lazarev.exceptions.ProductException;

public class ProductNotFoundException extends ProductException {
    public ProductNotFoundException(String msg) {
        super(msg);
    }
    public ProductNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
