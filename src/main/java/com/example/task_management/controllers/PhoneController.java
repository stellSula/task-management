package com.example.task_management.controllers;

import com.example.task_management.exeptions.EmptyPhoneListException;
import com.example.task_management.models.Phone;
import com.example.task_management.services.PhoneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/phone")
public class PhoneController {

    public final PhoneService phoneService;
    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

    public PhoneController(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @GetMapping("/get-phones")
    public List<Phone> getPhones() {
        logger.info("Fetching all phones");
        List<Phone> phones = phoneService.getPhones();

        if (phones.isEmpty()) {
            logger.warn("No phones found");
            throw new EmptyPhoneListException();
        }

        logger.info("Retrieved {} phones", phones.size());
        return phones;
    }
}
