package com.mtuci.lazarev.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class AuthenticationResponse {
    private String token;
    private String login;
    private Boolean full_access;
}
