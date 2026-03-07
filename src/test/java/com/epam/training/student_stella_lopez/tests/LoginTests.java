package com.epam.training.student_stella_lopez.tests;

import com.epam.training.student_stella_lopez.core.BaseTest;
import com.epam.training.student_stella_lopez.pages.InventoryPage;
import com.epam.training.student_stella_lopez.pages.LoginPage;
import static org.assertj.core.api.Assertions.assertThat;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    // Data Providers

    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidLoginData() {
        return new Object[][]{
                {"username", "password", "Username is required"},
                {"standard_user", "password", "Password is required"}
        };
    }

    // Test: Invalid Credentials

    @Test(dataProvider = "invalidLoginData",
            description = "Verify that an error message is shown when login is attempted with missing credentials")
    public void should_show_error_message_when_login_attempted_with_missing_credentials(
            String username, String password, String expectedError) {

        // Given: A user is on the login page with pre-filled credentials
        LoginPage loginPage = new LoginPage();
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);

        // When: The user clears the required field and submits the form
        if (username.equals("username")) {
            loginPage.clearUsername();
        }
        if (password.equals("password")) {
            loginPage.clearPassword();
        }
        loginPage.clickLogin();

        // Then: An appropriate error message should be displayed
        String actualError = loginPage.getErrorMessage();

        assertThat(actualError)
                .as("Expected error message to contain the correct validation message")
                .contains(expectedError);
    }

    // Test: Valid Credentials

    @Test(description = "Verify that a user with valid credentials is redirected to the inventory page")
    public void should_navigate_to_inventory_page_when_login_with_valid_credentials() {

        // Given: A user is on the login page
        LoginPage loginPage = new LoginPage();

        // When: The user logs in with valid credentials
        loginPage.loginAs("standard_user", "secret_sauce");

        // Then: The inventory page should be displayed with the correct header
        InventoryPage inventoryPage = new InventoryPage();
        String actualHeader = inventoryPage.getHeaderText();
        assertThat(actualHeader)
                .as("Expected inventory page header to be 'Swag Labs'")
                .isEqualTo("Swag Labs");
    }
}