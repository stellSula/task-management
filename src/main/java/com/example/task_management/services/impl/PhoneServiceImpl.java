package com.example.task_management.services.impl;

import com.example.task_management.apis.PhoneApiClient;
import com.example.task_management.models.Phone;
import com.example.task_management.services.PhoneService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class PhoneServiceImpl implements PhoneService {

    private final PhoneApiClient phoneApiClient;

    public PhoneServiceImpl(PhoneApiClient phoneApiClient) {
        this.phoneApiClient = phoneApiClient;
    }
    @Override
    public List<Phone> getPhones() {
        try {
            return phoneApiClient.fetchPhones();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
