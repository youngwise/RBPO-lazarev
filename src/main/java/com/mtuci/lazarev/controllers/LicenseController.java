package com.mtuci.lazarev.controllers;

import lombok.Getter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/license")
@Getter
public class LicenseController {

}

// БД ПО
// id, имя, статус (действительный или нет),