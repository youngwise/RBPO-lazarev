package com.mtuci.lazarev.service.impl;

import com.mtuci.lazarev.exceptions.categories.License.LicenseErrorActivationException;
import com.mtuci.lazarev.exceptions.categories.License.LicenseNotFoundException;
import com.mtuci.lazarev.exceptions.categories.LicenseTypeNotFoundException;
import com.mtuci.lazarev.exceptions.categories.ProductNotFoundException;
import com.mtuci.lazarev.exceptions.categories.UserNotFoundException;
import com.mtuci.lazarev.models.*;
import com.mtuci.lazarev.repositories.DeviceLicenseRepository;
import com.mtuci.lazarev.repositories.LicenseRepository;
import com.mtuci.lazarev.service.LicenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LicenseServiceImpl implements LicenseService {
    private final LicenseRepository licenseRepository;
    private final ProductServiceImpl productService;
    private final UserServiceImpl userService;
    private final LicenseTypeServiceImpl licenseTypeService;
    private final LicenseHistoryServiceImpl licenseHistoryService;
    private final DeviceLicenseRepository deviceLicenseRepository;

    @Override
    public License createLicense(
            Long productId, Long ownerId, Long licenseTypeId,
            Integer device_count, Integer duration) {
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
        license.setBlocked(false);
        license.setUser(null);

        // Активация только при введении кода
        license.setFirst_activation_date(null);

        // Генерация активационного кода
        String code = generateCodeLicense(productId, ownerId, licenseTypeId, device_count);
        license.setCode(code);
        license.setDevice_count(device_count);

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

        licenseHistoryService.recordLicenseChange(license, owner, LicenseHistoryStatus.CREATE.name(), description.toString());
        return licenseRepository.save(license);
    }


    @Override
    public Ticket activateLicense(String activationCode, Device device, ApplicationUser user) {
        License license = licenseRepository.findByCode(activationCode).orElseThrow(
                () -> new LicenseNotFoundException("Лицензция не найдена")
        );

        if (!validateLicense(license, device, user))
        {
            licenseHistoryService.recordLicenseChange(license, user, LicenseHistoryStatus.ACTIVATE.name(), "Активация лицензии невозможна");
            throw new LicenseErrorActivationException("Активация невозможна");
        }

        license.setUser(user);

        createDeviceLicense(license, device);
        updateLicense(license);
        licenseHistoryService.recordLicenseChange(license, user, LicenseHistoryStatus.ACTIVATE.name(), "Лицензия успешно активирована");
        return generateTicket(license, device);
    }

    @Override
    public boolean validateLicense(License license, Device device, ApplicationUser user) {
        /*
        ======================================================================
            лицензция заблокирована или
            лицензия уже активирована или
            уже есть пользователь или
            лицензия на данное устройство уже активирована или
            достигнуто максимальное кол-во устройств для активации или
            срок действия лицензии истёк
            то лицензию невозожно активировать
        ======================================================================
        */

        return !license.isBlocked() &&
                license.getFirst_activation_date() == null &&
                license.getUser() == null &&
                license.getDeviceLicenses().stream().noneMatch(deviceLicense ->
                        deviceLicense.getDevice().equals(device) &&
                        deviceLicense.getLicense().equals(license)
                        ) &&
                license.getDeviceLicenses().size() < license.getDevice_count() &&
                new Date(System.currentTimeMillis()).before(license.getEnding_date());
    }

    @Override
    public void createDeviceLicense(License license, Device device) {
        DeviceLicense deviceLicense = new DeviceLicense();
        deviceLicense.setDevice(device);
        deviceLicense.setLicense(license);
        deviceLicenseRepository.save(deviceLicense);
    }

    @Override
    public void updateLicense(License license) {
        license.setFirst_activation_date(new Date(System.currentTimeMillis()));

        Format formatter = new SimpleDateFormat("dd.MM.yyyy");
        license.setDescription(license.getDescription() + String.format("\nПользователь: %s\n"+
                        "Впервые активирована: %s\nАктивированных устройств: %d",
                license.getUser().getLogin(),
                formatter.format(license.getFirst_activation_date()),
                license.getDeviceLicenses().size()
                )
        );

        licenseHistoryService.recordLicenseChange(license, license.getUser(), LicenseHistoryStatus.MODIFICATION.name(), license.getDescription());
        licenseRepository.save(license);
    }

    @Override
    public List<License> getActiveLicensesForDevice(Device device, ApplicationUser user) {
        /*
        ======================================================================
        лицензия активна если:
            - лицензция не заблокирована
            - лицензиию имеет текущий пользователь
            - срок действия лицензии не истёк
        ======================================================================
        */
        return device.getDeviceLicenses().stream()
                .map(DeviceLicense::getLicense)
                .filter(license ->
                        license.getUser().equals(user) &&
                        !license.isBlocked() &&
                        license.getEnding_date().before(new Date(System.currentTimeMillis()))
                ).toList();
    }

    @Override
    public Ticket generateTicket(License license, Device device) {
        Ticket ticket = new Ticket();
        ticket.setNowDate(new Date(System.currentTimeMillis()));
        ticket.setActivationDate(license.getFirst_activation_date());
        ticket.setExpirationDate(license.getEnding_date());
        ticket.setExpiration(license.getDuration());
        ticket.setUserID(license.getUser().getId());
        ticket.setDeviceID(device.getId());
        ticket.setBlockedLicence(license.isBlocked());
        ticket.setDescription("Генарция токена");

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String ds = bCryptPasswordEncoder.encode(
                ticket.getNowDate().toString() + ticket.getActivationDate().toString() +
                        ticket.getExpirationDate().toString() + ticket.getExpiration().toString() +
                        ticket.getUserID().toString() + ticket.getDeviceID().toString()
        );
        ticket.setDigitalSignature(ds);
        return ticket;
    }

    @Override
    public List<Ticket> licenseRenewal(String activationCode, ApplicationUser user) {
        // Проверка ключа лицензии
        License license = licenseRepository.findByCode(activationCode).orElseThrow(
                () -> new LicenseNotFoundException("Ключ лицензии недействителен")
        );

        List<Ticket> tickets = license.getDeviceLicenses().stream()
                .map(deviceLicense -> generateTicket(license, deviceLicense.getDevice())).toList();
        // Проверка возможности продления
        if (license.isBlocked() || license.getEnding_date().before(new Date(System.currentTimeMillis())))
        {
            tickets.forEach(ticket -> {
                ticket.setDescription("Невозможно продлить лицензию");
                licenseHistoryService.recordLicenseChange(license, user, LicenseHistoryStatus.MODIFICATION.name(), ticket.getDescription());
            });
            return tickets;
        }

        // Продление на год
        license.setDuration(license.getDuration() + 31536000);
        license.setEnding_date(new Date(license.getEnding_date().getTime()/1000 + 31536000));

        tickets.forEach(ticket -> {
            ticket.setDescription("Лицензия успешно продлена");
            licenseHistoryService.recordLicenseChange(license, user, LicenseHistoryStatus.MODIFICATION.name(), ticket.getDescription());
        });
        return tickets;
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
