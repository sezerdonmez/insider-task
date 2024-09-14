package com.insider.helpers;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;
import java.util.UUID;

public class RandomizeHelper {

    public static int generateRandomNumber (int size) {
        Random random = new Random();
        return random.nextInt(size);
    }

    public String generateRandomString (int length, boolean isIncludeNumbers, boolean isIncludeLetters) {
        return RandomStringUtils.random(length, isIncludeLetters, isIncludeNumbers);
    }

    public String generateRandomEmail () {
        return UUID.randomUUID() + "@insiderqamail.com";
    }
}
