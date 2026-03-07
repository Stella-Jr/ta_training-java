package com.epam.training.student_stella_lopez.core;

import org.openqa.selenium.WebDriver;

public final class DriverManager {

    // Private constructor to prevent instantiation of this utility class
    private DriverManager() {}

    // ThreadLocal ensures that each test thread has its own WebDriver instance
    // This is important for parallel test execution
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    // Store the WebDriver instance for the current thread
    public static void setDriver(WebDriver driver) {
        DRIVER.set(driver);
    }

    // Retrieve the WebDriver for the current thread
    public static WebDriver getDriver() {
        WebDriver driver = DRIVER.get();

        // If driver is null, it means the test was not initialized properly
        if (driver == null) {
            throw new IllegalStateException(
                    "WebDriver is not initialized for this thread. Did you forget to run BaseTest @BeforeMethod?"
            );
        }

        return driver;
    }

    // Close and remove the driver from the current thread
    public static void quitDriver() {
        WebDriver driver = DRIVER.get();
        if (driver != null) {
            driver.quit();
            DRIVER.remove();
        }
    }
}