package com.epam.training.student_stella_lopez.core;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import org.slf4j.bridge.SLF4JBridgeHandler;

public abstract class BaseTest {

    // This block redirects Java Util Logging to SLF4J so logs are handled consistently
    static {
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();
    }

    // This method runs before each test to initialize the browser and open the base URL
    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional String browserFromXml) {

        // Determine which browser to use (from testng.xml or from config.properties)
        String browser = (browserFromXml != null && !browserFromXml.isBlank())
                ? browserFromXml
                : ConfigReader.get("browser", "chrome");

        // Read timeout and base URL from the configuration file
        long timeoutSeconds = Long.parseLong(ConfigReader.get("timeout.seconds", "10"));
        String baseUrl = ConfigReader.get("base.url", "https://www.saucedemo.com/");

        // Create the WebDriver instance using the factory
        WebDriver driver = DriverFactory.createDriver(browser, timeoutSeconds);
        DriverManager.setDriver(driver);

        // Navigate to the application under test
        driver.get(baseUrl);
    }

    // This method runs after each test to properly close the browser
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverManager.quitDriver();
    }
}