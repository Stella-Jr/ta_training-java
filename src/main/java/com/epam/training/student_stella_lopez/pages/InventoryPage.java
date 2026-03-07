package com.epam.training.student_stella_lopez.pages;

import com.epam.training.student_stella_lopez.core.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage {

    private final WebDriver driver = DriverManager.getDriver();

    // CSS locators
    private final By headerTitle = By.cssSelector(".app_logo");

    public String getHeaderText(){
        return driver.findElement(headerTitle).getText();
    }

}
