package com.mtuci.lazarev.service.impl;

import com.mtuci.lazarev.exceptions.categories.LicenseTypeNotFoundException;
import com.mtuci.lazarev.exceptions.categories.ProductNotFoundException;
import com.mtuci.lazarev.exceptions.categories.UserNotFoundException;
import com.mtuci.lazarev.models.*;
import com.mtuci.lazarev.repositories.LicenseRepository;
import com.mtuci.lazarev.service.LicenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LicenseServiceImpl implements LicenseService {
    private final LicenseRepository licenseRepository;
    private final ProductServiceImpl productService;
    private final UserServiceImpl userService;
    private final LicenseTypeServiceImpl licenseTypeService;
    private final LicenseHistoryServiceImpl licenseHistoryService;

    @Override
    public License createLicense(Long productId, Long ownerId, Long licenseTypeId, Integer device_count, Integer duration) {
        Product product = productService.getProductById(productId).orElseThrow(
                () -> new ProductNotFoundException("Продукт не найден"));
        ApplicationUser owner = userService.getUserById(ownerId).orElseThrow(
                () -> new UserNotFoundException("Пользователь не найден"));
        LicenseType licenseType = licenseTypeService.getLicenseById(licenseTypeId).orElseThrow(
                () -> new LicenseTypeNotFoundException("Тип лицензии не найден"));

        // Создание новой лицензии
        License license = new License();

        // Установка свойств лицензии
        license.setDevice_count(device_count);

        // Активация только при введении кода
        license.setFirst_activation_date(null);

        // Генерация активационного кода
        String code = generateCodeLicense(productId, ownerId, licenseTypeId, device_count);
        license.setCode(code);

        // Установка владельца, продукта, типа
        license.setProduct(product);
        license.setOwner(owner);
        license.setLicenseType(licenseType);

        // Проверка параметров
        if (duration == 0)
            duration = licenseType.getDefault_duration();
        license.setDuration(duration);

        // Расчёт даты окончания
        Date endDate = new Date(System.currentTimeMillis() + duration);
        license.setEnding_date(endDate);

        StringBuilder description = new StringBuilder();
        description.append(String.format("Тип лицензии: %s\n", licenseType.getName()));
        description.append(String.format("Продукт: %s\n", product.getName()));
        description.append(String.format("Владелец: %s\n", owner.getLogin()));
        description.append(String.format("Кол-во устройств: %d\n", device_count));

        Format formatter = new SimpleDateFormat("dd.MM.yyyy");
        description.append(String.format("Действует до: %s\n", formatter.format(endDate)));

        license.setDescription(description.toString());

        if (licenseHistoryService.recordLicenseChange(license, owner, LicenseHistoryStatus.CREATE.name(), description.toString())) {
            licenseRepository.save(license);
            return license;
        }

        return null;
    }

    @Override
    public Optional<License> findById(Long id) {
        return licenseRepository.findById(id);
    }

    private String generateCodeLicense(Long productId, Long ownerId, Long licenseTypeId, Integer device_count) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(productId.toString() + ownerId.toString() + licenseTypeId.toString() + device_count.toString());
    }
}
