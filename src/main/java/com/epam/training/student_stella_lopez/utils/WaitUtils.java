package com.epam.training.student_stella_lopez.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    // Utility method to wait until an input field is completely empty
    // This helps avoid timing issues when clearing fields before submitting forms
    public static void waitUntilInputIsEmpty(WebDriver driver, By locator, long timeoutSeconds) {

        // Explicit wait is used instead of Thread.sleep for better test stability
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));

        // Wait until the value attribute of the input becomes empty
        wait.until(ExpectedConditions.attributeToBe(locator, "value", ""));
    }
}