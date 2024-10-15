package com.mtuci.lazarev.service.impl;

import com.mtuci.lazarev.models.Main;
import com.mtuci.lazarev.repositories.DetailsRepository;
import com.mtuci.lazarev.repositories.MainRepository;
import com.mtuci.lazarev.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MainServiceImpl implements MainService {

    private final MainRepository mainRepository;
    private final DetailsRepository detailsRepository;

    @Override
    public void save(Main main) {
        main.getDetails().forEach(details -> {
            details.setMain(main);
            detailsRepository.save(details);
        });
        mainRepository.save(main);
    }

    @Override
    public List<Main> findAll() {
        return mainRepository.findAll();
    }

    @Override
    public Main findById(long id) { return mainRepository.findById(id).orElse(new Main()); }
}
