package com.mtuci.lazarev.service;


import com.mtuci.lazarev.models.ApplicationUser;

import java.util.Optional;

public interface UserService {
    Optional<ApplicationUser> getUserById(Long id);
    Optional<ApplicationUser> getUserByLogin(String email);
}
