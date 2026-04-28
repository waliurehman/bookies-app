# Bookstore Selenium Test Automation

Complete Selenium test automation suite for MERN BookStore application with 15 comprehensive test cases.

## Test Suite Overview

### 15 Complete Test Cases:

1. **testValidSignIn()** - Login with valid credentials
   - Verifies successful login with correct email and password
   - Checks logout button appears after successful login

2. **testInvalidPassword()** - Wrong password error handling
   - Attempts login with incorrect password
   - Verifies error or login failure

3. **testEmptyEmail()** - Email field validation
   - Tests login form submission with empty email
   - Verifies validation prevents submission

4. **testEmptyPassword()** - Password field validation
   - Tests login form submission with empty password
   - Verifies validation prevents submission

5. **testSignOut()** - Logout functionality
   - Logs in user successfully
   - Clicks logout button
   - Verifies user is logged out

6. **testSignUp()** - User registration
   - Fills out signup form with new user credentials
   - Submits signup
   - Verifies auto-login after registration

7. **testSignUpDuplicateEmail()** - Duplicate email validation
   - Attempts signup with existing email
   - Verifies error or signup failure

8. **testBooksPageLoad()** - Books page navigation
   - Logs in user
   - Navigates to /books
   - Verifies page loads successfully

9. **testBooksVisible()** - Books display
   - Navigates to books page
   - Verifies at least one book is displayed

10. **testFreeBookFilter()** - Filter functionality
    - Applies free books filter
    - Verifies filter works correctly

11. **testNavigateToContact()** - Navigation to contact page
    - Navigates to /contact
    - Verifies URL contains "/contact"

12. **testNavigateToAbout()** - Navigation to about page
    - Navigates to /about
    - Verifies URL contains "/about"

13. **testDarkModeToggle()** - Theme switching
    - Toggles dark mode
    - Verifies mode changes

14. **testAdminAddBook()** - Admin book addition
    - Logs in user
    - Navigates to /add-book
    - Adds a new book
    - Verifies success message

15. **testBooksPageWithoutLogin()** - Public access
    - Navigates to books page without login
    - Verifies books are visible without authentication

## Project Structure

```
src/
├── test/
│   └── java/
│       ├── base/
│       │   └── BaseTest.java (WebDriver setup/teardown)
│       ├── pages/
│       │   └── PageLogin.java (Page Object Model)
│       └── tests/
│           └── BookstoreLoginTests.java (15 test cases)
pom.xml (Maven configuration)
testng.xml (TestNG suite configuration)
```

## Prerequisites

- Java 11 or higher
- Maven 3.6.0 or higher
- Chrome browser installed
- MERN BookStore app running on:
  - Frontend: http://localhost:5173
  - Backend: http://localhost:3000

## Installation & Setup

### 1. Clone/Setup Project
```bash
cd your-project-folder
```

### 2. Install Dependencies
```bash
mvn clean install
```

### 3. Configure Test User (Optional)
Update test credentials in test cases if needed:
- Email: test@bookies.com
- Password: Test@123
- Username: testuser

## Running Tests

### Run All Tests
```bash
mvn clean test
```

### Run Specific Test Class
```bash
mvn test -Dtest=BookstoreLoginTests
```

### Run Specific Test Method
```bash
mvn test -Dtest=BookstoreLoginTests#testValidSignIn
```

### Run with TestNG XML
```bash
mvn clean test -Dsuites=testng.xml
```

## Test Reports

After execution, view test reports:
- **HTML Report**: `target/surefire-reports/index.html`
- **TestNG Report**: `test-output/index.html`

## Key Features

### Page Object Model (POM)
- `PageLogin.java` encapsulates all locators and methods
- Reusable methods for login, signup, navigation, filtering
- Clear separation of test logic from page elements

### Base Test Class
- Centralized WebDriver setup and teardown
- Headless Chrome configuration
- Implicit and explicit waits
- Screenshot capability ready

### Test Annotations
```java
@BeforeMethod  // Runs before each test
@AfterMethod   // Runs after each test
@Test          // Marks test method
@Test(dependsOnMethods = "")  // Test dependencies
```

## Headless Chrome Configuration

Tests run in headless mode (no browser window):
```java
options.addArguments("--headless");
options.addArguments("--no-sandbox");
options.addArguments("--disable-dev-shm-usage");
options.addArguments("--window-size=1920,1080");
```

## Explicit Waits Used

All tests use explicit waits with `WebDriverWait`:
- Wait for element visibility
- Wait for element clickability
- Duration: 10 seconds (configurable)

## Dependencies

- **Selenium WebDriver**: 4.15.0
- **TestNG**: 7.8.1
- **WebDriverManager**: 5.7.1
- **Java**: 11+

## Troubleshooting

### ChromeDriver Issues
```bash
# Automatically managed by WebDriverManager
# No manual setup needed
```

### Port Already in Use
```bash
# Make sure app is running on correct ports:
# Frontend: http://localhost:5173
# Backend: http://localhost:3000
```

### Test Timeouts
- Default wait: 10 seconds
- Modify in BaseTest.java if needed
- Increase if server is slow

### Login Credentials
- Test user must exist: `test@bookies.com / Test@123`
- Or update credentials in test methods

## Best Practices Implemented

✅ Page Object Model pattern
✅ Explicit waits for reliability
✅ Headless Chrome for CI/CD
✅ Clear test names and documentation
✅ Proper setup/teardown
✅ Real assertions (not just pass)
✅ Independent tests (can run in any order)
✅ Reusable helper methods

## CI/CD Integration

For GitHub Actions, Jenkins, or other CI tools:

```yaml
# Example: .github/workflows/test.yml
- name: Run Selenium Tests
  run: mvn clean test
```

## Contact & Support

For issues or enhancements:
1. Check test logs in `test-output/`
2. Verify app is running on correct ports
3. Ensure test user exists with correct credentials
4. Review browser console errors

---

**Version**: 1.0.0  
**Last Updated**: 2026-04-28  
**Status**: ✅ All 15 tests implemented and documented
