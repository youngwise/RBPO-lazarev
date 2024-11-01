package com.mtuci.lazarev.service;

import com.mtuci.lazarev.models.Main;

import java.util.List;

public interface MainService {
    void save(Main demo);
    List<Main> findAll();
    Main findById(long id);
}
