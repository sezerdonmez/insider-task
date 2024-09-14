package com.insider.helpers;

public class StringHelper {

    public static String getNumericValueFromText(String text) {
        return text.replaceAll("[^0-9.]", "");
    }
}
