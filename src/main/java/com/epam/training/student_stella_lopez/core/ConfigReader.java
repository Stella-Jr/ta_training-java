package com.epam.training.student_stella_lopez.core;

import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader {

    // This object will store all properties loaded from the config file
    private static final Properties PROPS = new Properties();

    // Static block runs once when the class is loaded
    static {
        try (InputStream is = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {

            // If the file is not found, the framework should stop because configuration is required
            if (is == null) {
                throw new IllegalStateException("config.properties not found in src/test/resources");
            }

            // Load all key-value pairs from the properties file
            PROPS.load(is);

        } catch (Exception e) {
            // If loading fails, throw a runtime error to avoid running tests with missing configuration
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    // Private constructor to prevent creating instances of this utility class
    private ConfigReader() {}

    // Method used to retrieve values from the config file with a default fallback
    public static String get(String key, String defaultValue) {
        return PROPS.getProperty(key, defaultValue);
    }
}