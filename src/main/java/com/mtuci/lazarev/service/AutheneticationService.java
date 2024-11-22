package com.mtuci.lazarev.service;


import com.mtuci.lazarev.models.ApplicationUser;

public interface AutheneticationService {
    boolean authenticate(ApplicationUser user, String password);
}
