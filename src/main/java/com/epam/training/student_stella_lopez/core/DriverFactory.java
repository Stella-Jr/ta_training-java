package com.epam.training.student_stella_lopez.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import java.time.Duration;

public final class DriverFactory {

    // Private constructor to prevent instantiation of this utility class
    private DriverFactory() {}

    public static WebDriver createDriver(String browser, long timeoutSeconds) {

        // Normalize browser input and set Chrome as default if none is provided
        String b = (browser == null || browser.isBlank()) ? "chrome" : browser.toLowerCase().trim();

        WebDriver driver;

        // Select browser based on configuration
        switch (b) {
            case "firefox" -> {

                // WebDriverManager automatically downloads and manages the correct driver
                WebDriverManager.firefoxdriver().setup();

                FirefoxOptions options = new FirefoxOptions();
                driver = new FirefoxDriver(options);
            }

            case "chrome" -> {

                // Setup Chrome driver automatically
                WebDriverManager.chromedriver().setup();

                ChromeOptions options = new ChromeOptions();
                driver = new ChromeDriver(options);
            }

            // If an unsupported browser is passed, stop execution with a clear error
            default -> throw new IllegalArgumentException(
                    "Unsupported browser: " + browser + ". Supported: chrome, firefox"
            );
        }

        // Maximize the browser window to ensure elements are visible during tests
        driver.manage().window().maximize();

        // Configure timeouts for page loading and script execution
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(timeoutSeconds));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(timeoutSeconds));

        return driver;
    }
}