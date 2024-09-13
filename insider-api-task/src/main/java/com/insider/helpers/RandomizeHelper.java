package com.insider.helpers;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;
import java.util.UUID;

public class RandomizeHelper {

    public static Integer getRandomNumberInRange(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + min);
    }

    public static String getRandomStringWithLength(int length, boolean useLetters, boolean useMembers) {
        return RandomStringUtils.random(length, useLetters, useMembers);
    }

    public static String randomUUIDGenerator() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public static String getRandomMail() {
        return randomUUIDGenerator() + "@insiderapitask.com";
    }
}
