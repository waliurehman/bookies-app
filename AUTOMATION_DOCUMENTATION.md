# Selenium Test Automation - Complete Documentation

## 📋 Overview

Complete Selenium test automation suite for MERN BookStore with **15 comprehensive test cases** covering:
- Login/Logout functionality
- User registration and validation
- Book browsing and filtering
- Navigation between pages
- Dark mode toggling
- Admin features (add books)
- Public access without authentication

---

## 📁 File Structure

### 1. **PageLogin.java** - Page Object Model
**Location**: `src/test/java/pages/PageLogin.java`

**Responsibility**: All UI interactions and element locators

**Key Methods**:
- Navigation: `navigateToHome()`, `navigateToBooks()`, `navigateToAdminDashboard()`
- Login: `login()`, `clickLoginButton()`, `enterEmail()`, `enterPassword()`
- Sign Up: `signup()`, `clickSignUpButton()`, `enterUsername()`
- Navigation: `clickBooksLink()`, `clickContactLink()`, `clickAboutLink()`
- Filters: `filterFreeBooks()`, `filterPaidBooks()`
- Assertions: `isLoginButtonVisible()`, `isLogoutButtonVisible()`

**Locator Strategy**:
- Uses XPath for most elements (more flexible)
- Waits using `WebDriverWait` with 10-second timeout
- All waits are explicit (not implicit)

---

### 2. **BaseTest.java** - Test Foundation
**Location**: `src/test/java/base/BaseTest.java`

**Responsibility**: WebDriver setup and teardown

**Features**:
```java
@BeforeClass    // Runs once before each test class
  - Initializes ChromeDriver
  - Configures headless mode
  - Sets window size (1920x1080)
  - Disables automation detection
  - Sets 10-second implicit wait

@AfterClass     // Runs once after each test class
  - Closes WebDriver
  - Cleans up resources
```

**Headless Chrome Configuration**:
- `--headless`: No browser window
- `--no-sandbox`: Security
- `--disable-dev-shm-usage`: Memory optimization
- `--disable-blink-features=AutomationControlled`: Avoid detection

---

### 3. **BookstoreLoginTests.java** - Test Cases
**Location**: `src/test/java/tests/BookstoreLoginTests.java`

**15 Test Cases** (all with TestNG annotations):

```
@BeforeMethod → Navigate to home
Test Method   → Execute test steps
@AfterMethod  → Clear cookies
```

#### Test Cases Breakdown:

| # | Test Name | Purpose | Key Assertion |
|---|-----------|---------|---------------|
| 1 | testValidSignIn | Login with correct credentials | Logout button visible |
| 2 | testInvalidPassword | Wrong password handling | Login fails |
| 3 | testEmptyEmail | Email field validation | Login prevented |
| 4 | testEmptyPassword | Password field validation | Login prevented |
| 5 | testSignOut | Logout functionality | Login button visible after logout |
| 6 | testSignUp | Register new user | Auto-login after signup |
| 7 | testSignUpDuplicateEmail | Duplicate email validation | Signup fails |
| 8 | testBooksPageLoad | Navigate to books | URL contains "/books" |
| 9 | testBooksVisible | Display books | At least 1 book visible |
| 10 | testFreeBookFilter | Filter free books | Filter applies |
| 11 | testNavigateToContact | Go to contact page | URL contains "/contact" |
| 12 | testNavigateToAbout | Go to about page | URL contains "/about" |
| 13 | testDarkModeToggle | Switch theme | Dark mode state changes |
| 14 | testAdminAddBook | Add book as admin | Success message shown |
| 15 | testBooksPageWithoutLogin | Public access | Books visible without auth |

---

### 4. **pom.xml** - Maven Configuration
**Location**: `pom.xml`

**Dependencies**:
```xml
<dependency>
  <groupId>org.seleniumhq.selenium</groupId>
  <artifactId>selenium-java</artifactId>
  <version>4.15.0</version>
</dependency>

<dependency>
  <groupId>org.testng</groupId>
  <artifactId>testng</artifactId>
  <version>7.8.1</version>
</dependency>

<dependency>
  <groupId>io.github.bonigarcia</groupId>
  <artifactId>webdrivermanager</artifactId>
  <version>5.7.1</version>
</dependency>
```

**Plugins**:
- Maven Compiler: Java 11
- Maven Surefire: Runs tests with TestNG
- Maven Failsafe: Additional test execution

---

### 5. **testng.xml** - Test Suite Configuration
**Location**: `testng.xml`

**Defines**:
- Test suite name: "Bookstore Test Suite"
- Test class: `BookstoreLoginTests`
- All 15 test methods (in order)
- HTML and JQ listeners for reporting

**Execution**:
```bash
mvn clean test
```

---

### 6. **TestUtils.java** - Helper Methods
**Location**: `src/test/java/utils/TestUtils.java`

**Utility Methods**:
- `takeScreenshot(testName)` - Save PNG screenshots
- `waitForTime(milliseconds)` - Custom delays
- `getPageTitle()` - Get page title
- `getCurrentUrl()` - Get current URL
- `refreshPage()` - Refresh browser
- `goBack()` / `goForward()` - Navigate
- `clearCookies()` / `clearCache()` - Clean browser
- `printTestInfo()` - Console logging

---

## 🔧 How It Works Together

### Execution Flow:

```
1. Maven Command
   ↓
2. pom.xml (Load dependencies)
   ↓
3. BaseTest.java (Setup WebDriver)
   ↓
4. testng.xml (Load test suite)
   ↓
5. BookstoreLoginTests.java (@BeforeMethod)
   ↓
6. Test Method (Execute test steps)
   ├─ Uses PageLogin.java (UI interactions)
   ├─ Uses TestUtils.java (Helper methods)
   └─ Assertions (Pass/Fail)
   ↓
7. @AfterMethod (Cleanup)
   ↓
8. BaseTest.java (@AfterClass - Teardown)
   ↓
9. Test Report Generated
```

---

## 📊 Test Execution Example

### Test Case Flow: `testValidSignIn()`

```java
@Test(priority = 1)
public void testValidSignIn() {
    // 1. Setup
    pageLogin.navigateToHome();  // WebDriver.navigate()
    
    // 2. Execute
    pageLogin.clickLoginButton();           // Find button → Click
    pageLogin.enterEmail("test@bookies.com");     // Find input → SendKeys
    pageLogin.enterPassword("Test@123");          // Find input → SendKeys
    pageLogin.clickLoginSubmit();                 // Find button → Click
    
    // 3. Wait & Assert
    wait.until(ExpectedConditions.visibilityOfElementLocated(
        By.xpath("//button[contains(text(), 'Logout')]")));
    
    // 4. Verify
    Assert.assertTrue(pageLogin.isLogoutButtonVisible(), 
                     "User should be logged in");
    
    // 5. Cleanup (automatic via @AfterMethod)
    driver.manage().deleteAllCookies();
}
```

---

## 🚀 Running Tests

### Run All Tests
```bash
mvn clean test
```

### Run Single Test
```bash
mvn test -Dtest=BookstoreLoginTests#testValidSignIn
```

### View Reports
- HTML: `target/surefire-reports/index.html`
- TestNG: `test-output/index.html`
- Screenshots: `screenshots/` folder

---

## ✅ Key Features Implemented

| Feature | Status | Details |
|---------|--------|---------|
| Page Object Model | ✅ | PageLogin.java encapsulates all UI |
| Explicit Waits | ✅ | WebDriverWait (10 sec timeout) |
| Headless Chrome | ✅ | No browser window, CI-friendly |
| TestNG Framework | ✅ | @Test, @BeforeMethod, @AfterMethod |
| Base Test Class | ✅ | Centralized setup/teardown |
| Real Assertions | ✅ | Assert.assertTrue/False/Equals |
| Helper Utils | ✅ | Screenshot, wait, navigation |
| Test Documentation | ✅ | JavaDoc comments on all methods |
| Error Handling | ✅ | Try-catch for timing issues |
| CI/CD Ready | ✅ | Headless, no manual intervention |

---

## 🎯 Test Coverage

**Total Test Cases**: 15

**Coverage Areas**:
- ✅ Authentication (3 tests)
- ✅ Registration (2 tests)
- ✅ Navigation (5 tests)
- ✅ Books Management (3 tests)
- ✅ Themes/UI (1 test)
- ✅ Admin Features (1 test)

---

## 📝 Prerequisites

1. **Java 11+** installed
2. **Maven 3.6+** installed
3. **Chrome Browser** installed
4. **BookStore App Running**:
   - Frontend: http://localhost:5173
   - Backend: http://localhost:3000
5. **Test User Created**:
   - Email: test@bookies.com
   - Password: Test@123

---

## 🔍 Debugging

### Common Issues & Solutions:

**Issue**: `NoSuchElementException`
```
Solution: Increase wait time in BaseTest.java
Duration.ofSeconds(10) → Duration.ofSeconds(15)
```

**Issue**: `Port already in use`
```
Solution: Ensure app running on correct ports
http://localhost:5173 (frontend)
http://localhost:3000 (backend)
```

**Issue**: `Test timeout`
```
Solution: Check app server logs for errors
Slow responses → Increase wait times
```

---

## 📚 Reference

**Selenium Documentation**: https://www.selenium.dev/
**TestNG Documentation**: https://testng.org/
**WebDriverManager**: https://github.com/bonigarcia/webdrivermanager

---

## ✨ Summary

A **production-ready** Selenium test automation suite with:
- ✅ 15 complete test cases
- ✅ Page Object Model pattern
- ✅ Proper error handling
- ✅ Headless browser execution
- ✅ Detailed documentation
- ✅ CI/CD integration ready

**Status**: ✅ **READY FOR PRODUCTION**

---

**Created**: April 28, 2026  
**Version**: 1.0.0  
**Framework**: Selenium 4.15.0 + TestNG 7.8.1
