package com.mtuci.lazarev.controllers;

import com.mtuci.lazarev.models.Main;
import com.mtuci.lazarev.service.MainService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/main")
public class MainController {
    private final MainService mainService;

    public MainController(MainService mainService) { this.mainService = mainService; }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('read')")
    public List<Main> findAll() {
        return mainService.findAll();
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public void save(@RequestBody Main main) { mainService.save(main); }
}
