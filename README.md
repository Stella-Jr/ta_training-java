# Selenium Automation Framework

**Author:** Stella Lopez  
**Language:** Java

## Project Description

This project implements a UI automation framework using Selenium WebDriver and TestNG to test the login functionality of the SauceDemo application.  
The framework follows the Page Object Model (POM) design pattern to separate test logic from page interactions, making the tests easier to maintain and scale.  
The project includes automated test cases that validate both valid and invalid login scenarios, and it also supports cross-browser execution and parallel testing.

## Technologies Used

| Technology          | Purpose                          |
|---------------------|----------------------------------|
| Java 21            | Programming language            |
| Selenium WebDriver | Browser automation              |
| TestNG             | Test execution framework        |
| Maven              | Build and dependency management |
| AssertJ            | Fluent assertions               |
| WebDriverManager   | Automatic driver management     |
| SLF4J + Logback    | Logging                         |

## Framework Design

The framework was designed with a modular structure to separate responsibilities between components.

```
src
 ├── core
 │    ├── BaseTest
 │    ├── DriverFactory
 │    ├── DriverManager
 │    └── ConfigReader
 │
 ├── pages
 │    ├── LoginPage
 │    └── InventoryPage
 │
 ├── tests
 │    └── LoginTests
 │
 ├── utils
 │    └── WaitUtils
 │
 └── resources
      └── config.properties
```

### Core
Contains the main framework infrastructure.

| Class          | Responsibility                                                                 |
|----------------|--------------------------------------------------------------------------------|
| BaseTest      | Test setup and teardown                                                        |
| DriverFactory | Creates WebDriver instances depending on the browser                          |
| DriverManager | Uses ThreadLocal to manage WebDriver instances safely during parallel execution |
| ConfigReader  | Loads configuration values from config.properties                              |

### Pages
Implements the Page Object Model. Each class represents a page of the application and contains:

- Element locators
- Interaction methods
- Reusable page actions

Examples: LoginPage, InventoryPage

### Tests
Contains the automated test cases. The tests are written using a BDD-style structure following the logic:  
**Given** → precondition / setup  
**When** → action performed  
**Then** → expected result / assertion  

Assertions are implemented using AssertJ for better readability.

### Utils
Contains helper utilities used across the framework.

- **WaitUtils** — Implements explicit waits to avoid synchronization issues during UI interactions.

## Test Scenarios

### Scenario 1 — Login with empty username
**Expected result:**  
Epic sadface: Username is required

### Scenario 2 — Login with empty password
**Expected result:**  
Epic sadface: Password is required

### Scenario 3 — Login with valid credentials
**Credentials:**  
Username: standard_user  
Password: secret_sauce  

**Expected result:**  
User is redirected to the inventory page and the header Swag Labs is displayed.

## BDD Approach

Although this project does not use Cucumber, it follows a BDD-inspired testing approach.  
Each test is written using a behavior-focused structure:

```java
// Given: the user is on the login page
// When:  the user performs an action
// Then:  the expected result is validated
```

This improves readability and helps describe the behavior of the application under test.

## Data-Driven Testing

The framework uses TestNG @DataProvider to run the same test with multiple sets of data.  
Benefits:

- Reduces duplicated test code
- Improves test scalability
- Makes it easy to add new test scenarios

## Cross-Browser Testing

The framework supports execution on multiple browsers:

✅ Chrome  
✅ Firefox  

The browser can be selected through testng.xml or configuration properties.

## Parallel Execution

Parallel execution is supported using ThreadLocal<WebDriver>, which ensures that each test thread uses an independent WebDriver instance.

## Logging

Logging is implemented using SLF4J + Logback. Logs help track test execution and diagnose issues during automation runs.  
Configuration file: src/test/resources/logback-test.xml

## How to Run the Tests

Run the test suite using Maven:  
```bash
mvn clean test
```  
Or run the testng.xml file directly from the IDE.

⚠️ **Important:** Always run from testng.xml to execute tests on both Chrome and Firefox in parallel.  
If you run LoginTests directly, only Chrome will be used — this is expected behavior, not a bug.  
The class-level run ignores the XML parameters and falls back to the default browser defined in config.properties.

## Key Features

✅ Page Object Model architecture  
✅ BDD-style test structure  
✅ Data-driven tests with @DataProvider  
✅ Cross-browser support (Chrome, Firefox)  
✅ Parallel execution with ThreadLocal  
✅ Logging with SLF4J + Logback  
✅ Explicit waits for stability
