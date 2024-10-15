package com.mtuci.lazarev.controllers;

import com.mtuci.lazarev.models.Details;
import com.mtuci.lazarev.models.Main;
import com.mtuci.lazarev.repositories.DetailsRepository;
import com.mtuci.lazarev.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/details")
@RequiredArgsConstructor
public class DetailsController {

    private final DetailsRepository detailsRepository;
    private final MainService mainService;

    @PostMapping("/{main_id}/save")
    public void save(@PathVariable(value = "main_id") Long mainId,
                     @RequestBody Details details) {
        Main main = mainService.findById(mainId);
        details.setMain(main);
        detailsRepository.save(details);
    }
}
