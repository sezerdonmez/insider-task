package com.insider.utils;


import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogUtils {

    private static final Logger LOGGER = LogManager.getLogger(LogUtils.class);
    private static final StringBuilder sb = new StringBuilder();

    private LogUtils() {

    }

    @Step("{0}")
    public static void logInfo(final String message) {
        try {
            sb.append(message);
        } catch (ArrayIndexOutOfBoundsException exception) {
            LOGGER.info(message);
        }

        LOGGER.info(message);
    }

    @Step("{0}")
    public static void logError(final String message) {
        try {
            sb.append(message);
        } catch (ArrayIndexOutOfBoundsException exception) {
            LOGGER.info(message);
        }

        LOGGER.error(message);
    }

    @Step("{0}")
    public static void logWarn(final String message) {
        try {
            sb.append(message);
        } catch (ArrayIndexOutOfBoundsException exception) {
            LOGGER.warn(message);
        }

        LOGGER.warn(message);
    }
}
