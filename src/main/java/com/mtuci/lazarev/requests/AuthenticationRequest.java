package com.mtuci.lazarev.requests;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String login, password;
}
