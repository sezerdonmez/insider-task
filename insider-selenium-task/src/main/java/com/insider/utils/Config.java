package com.insider.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.isBlank;

public class Config {

    public static final int FAILED_TEST_RETRY_COUNT;
    private static final Properties properties;
    public static final String ENVIRONMENT;

    static {
        ENVIRONMENT = getEnv();
        properties = loadProperties(ENVIRONMENT);
        FAILED_TEST_RETRY_COUNT = getFailedTestRetryCount();
    }

    private static Properties loadProperties(String env) {
        String configFileName = System.getProperty("user.dir") + "/src/test/resources/" + env + "_config.properties";
        Properties properties = new Properties();
        try {
            properties.load(Files.newInputStream(Paths.get(configFileName)));
        } catch (IOException e) {
            throw new IllegalStateException("Exception on loading config");
        }
        return properties;
    }

    public static String getEnv() {
        String defaultEnv = "test";
        String env = System.getProperties().getProperty("env");
        if (isBlank(env)) {
            LogUtils.logInfo(format("There is no env options set, please set -Denv in java. Now default environment will be set: %s", defaultEnv));
            return defaultEnv;
        }
        return env;
    }

    public static String getBrowser() {
        String defaultBrowser = loadProperties("test").getProperty("defaultBrowser");
        String browser = System.getProperties().getProperty("browser");

        if (isBlank(browser)) {
            LogUtils.logInfo(format("There is no browser options set, please set -Dbrowser in java. Now default browser will be set: %s", defaultBrowser));
            return defaultBrowser;
        }
        return browser;
    }

    private static int getFailedTestRetryCount() {
        return Integer.parseInt(System.getProperties().getProperty("retry", "0"));
    }

    public static String getProperty(String propertyKey) {
        return properties.getProperty(propertyKey);
    }
}
