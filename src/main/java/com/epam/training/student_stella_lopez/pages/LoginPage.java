package com.epam.training.student_stella_lopez.pages;

import com.epam.training.student_stella_lopez.core.DriverManager;
import com.epam.training.student_stella_lopez.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    // Get the driver instance from DriverManager
    private final WebDriver driver = DriverManager.getDriver();

    // URL of the login page of the application under test
    private final String PAGE_URL = "https://www.saucedemo.com/";

    // Locators for elements on the login page
    private final By usernameInput = By.cssSelector("#user-name");
    private final By passwordInput = By.cssSelector("#password");
    private final By loginButton = By.cssSelector("#login-button");
    private final By errorMessage = By.cssSelector("[data-test='error']");

    // Method to navigate directly to the login page
    public void open() {
        driver.get(PAGE_URL);
    }

    public void enterUsername(String username) {
        driver.findElement(usernameInput).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    // Clear the username field using CTRL+A and DELETE to ensure it is fully empty
    public void clearUsername() {
        driver.findElement(usernameInput).click();
        driver.findElement(usernameInput).sendKeys(Keys.CONTROL + "a");
        driver.findElement(usernameInput).sendKeys(Keys.DELETE);

        // Wait until the input value becomes empty to avoid timing issues
        WaitUtils.waitUntilInputIsEmpty(driver, usernameInput, 5);
    }

    // Clear the password field using the same approach
    public void clearPassword() {
        driver.findElement(passwordInput).click();
        driver.findElement(passwordInput).sendKeys(Keys.CONTROL + "a");
        driver.findElement(passwordInput).sendKeys(Keys.DELETE);

        WaitUtils.waitUntilInputIsEmpty(driver, passwordInput, 5);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    // High-level method that performs the full login action
    public void loginAs(String username, String password) {

        enterUsername(username);
        enterPassword(password);
        clickLogin();

    }

    // Retrieve the error message shown after a failed login attempt
    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }
}