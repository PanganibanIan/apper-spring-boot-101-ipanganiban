package com.apper.accountservice;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class IdGeneratorService {

    public String generateRandomCharacters(int length) {
        String generatedString = RandomStringUtils.randomAlphanumeric(length) ;
        return generatedString;
    }

    public String getNextId() {
        String id = UUID.randomUUID().toString();
        System.out.println("Generated id: " + id);

        return id ;
    }

}
