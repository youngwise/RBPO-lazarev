package com.mtuci.lazarev.service;


import com.mtuci.lazarev.models.ApplicationUser;
import com.mtuci.lazarev.requests.DataUserRequest;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<ApplicationUser> getUserById(Long id);
    Optional<ApplicationUser> getUserByLogin(String login);

    // save
    ApplicationUser save(DataUserRequest request);

    // read
    List<ApplicationUser> getAll();

    // update
    ApplicationUser update(DataUserRequest request);

    // delete
    void delete(Long id);
}
