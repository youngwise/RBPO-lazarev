package com.mtuci.lazarev.models;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String email, password;
}
