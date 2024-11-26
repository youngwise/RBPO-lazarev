package com.mtuci.lazarev.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DataProductRequest {
    private Long id;
    private String name;
    private boolean is_blocked;
}
