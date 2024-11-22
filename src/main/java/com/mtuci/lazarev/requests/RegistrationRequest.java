package com.mtuci.lazarev.requests;

import lombok.Data;

@Data
public class RegistrationRequest {
    private String login, email, password;
}
