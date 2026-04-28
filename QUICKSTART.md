# 🚀 Quick Start Guide - Selenium Tests

## ⚡ 5-Minute Setup

### Step 1: Install Java & Maven
```bash
# Verify Java installed (11+)
java -version

# Verify Maven installed (3.6+)
mvn -version
```

### Step 2: Run All Tests
```bash
# Navigate to project root
cd your-project-folder

# Run all 15 tests
mvn clean test
```

**Expected Output**:
```
[INFO] Tests run: 15, Failures: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

### Step 3: View Results
```
# Open HTML report
target/surefire-reports/index.html

# Or TestNG report
test-output/index.html
```

---

## 📝 Test Commands

### Run All Tests (15 cases)
```bash
mvn clean test
```

### Run Specific Test
```bash
# Run one test method
mvn test -Dtest=BookstoreLoginTests#testValidSignIn

# Run entire test class
mvn test -Dtest=BookstoreLoginTests
```

### Run & Skip Build
```bash
mvn test -DskipTests=false
```

### Generate Only Report (no run)
```bash
mvn surefire-report:report
```

---

## 📊 Test Cases Quick Reference

| # | Test Name | Command |
|---|-----------|---------|
| 1 | Valid Sign In | `mvn test -Dtest=BookstoreLoginTests#testValidSignIn` |
| 2 | Invalid Password | `mvn test -Dtest=BookstoreLoginTests#testInvalidPassword` |
| 3 | Empty Email | `mvn test -Dtest=BookstoreLoginTests#testEmptyEmail` |
| 4 | Empty Password | `mvn test -Dtest=BookstoreLoginTests#testEmptyPassword` |
| 5 | Sign Out | `mvn test -Dtest=BookstoreLoginTests#testSignOut` |
| 6 | Sign Up | `mvn test -Dtest=BookstoreLoginTests#testSignUp` |
| 7 | Duplicate Email | `mvn test -Dtest=BookstoreLoginTests#testSignUpDuplicateEmail` |
| 8 | Books Page Load | `mvn test -Dtest=BookstoreLoginTests#testBooksPageLoad` |
| 9 | Books Visible | `mvn test -Dtest=BookstoreLoginTests#testBooksVisible` |
| 10 | Free Book Filter | `mvn test -Dtest=BookstoreLoginTests#testFreeBookFilter` |
| 11 | Navigate Contact | `mvn test -Dtest=BookstoreLoginTests#testNavigateToContact` |
| 12 | Navigate About | `mvn test -Dtest=BookstoreLoginTests#testNavigateToAbout` |
| 13 | Dark Mode Toggle | `mvn test -Dtest=BookstoreLoginTests#testDarkModeToggle` |
| 14 | Admin Add Book | `mvn test -Dtest=BookstoreLoginTests#testAdminAddBook` |
| 15 | Books Without Login | `mvn test -Dtest=BookstoreLoginTests#testBooksPageWithoutLogin` |

---

## 🔧 Configuration

### Change Wait Timeout
**File**: `src/test/java/base/BaseTest.java`
```java
// Change implicit wait
driver.manage().timeouts().implicitlyWait(
    java.time.Duration.ofSeconds(15)  // Changed from 10
);
```

### Enable Browser Window (for debugging)
**File**: `src/test/java/base/BaseTest.java`
```java
// Comment out these lines:
// options.addArguments("--headless");

// Or remove it entirely
```

### Change Test URLs
**File**: `src/test/java/pages/PageLogin.java`
```java
public void navigateToHome() {
    driver.navigate().to("http://localhost:5173");  // Change port here
}
```

### Update Test Credentials
**File**: `src/test/java/tests/BookstoreLoginTests.java`
```java
pageLogin.enterEmail("newemail@test.com");      // Change email
pageLogin.enterPassword("newpassword");         // Change password
```

---

## 📁 Project Structure

```
project-root/
├── src/
│   └── test/
│       └── java/
│           ├── base/
│           │   └── BaseTest.java          ← WebDriver setup
│           ├── pages/
│           │   └── PageLogin.java         ← UI elements & methods
│           ├── tests/
│           │   └── BookstoreLoginTests.java ← 15 test cases
│           └── utils/
│               └── TestUtils.java         ← Helper methods
├── target/
│   └── surefire-reports/                  ← Test results
├── test-output/                           ← TestNG reports
├── screenshots/                           ← Test screenshots
├── pom.xml                                ← Maven config
├── testng.xml                             ← TestNG suite config
└── README files                           ← Documentation
```

---

## 🐛 Troubleshooting

### Issue: "Cannot find Chrome"
```
Solution: Install Google Chrome or update WebDriver
mvn clean install
```

### Issue: "Port 5173 not accessible"
```
Solution: Start your MERN app
cd client && npm run dev
```

### Issue: "Test user not found"
```
Solution: Create test user in app
Email: test@bookies.com
Password: Test@123
```

### Issue: "Element not found"
```
Solution: Increase wait time in BaseTest.java
Duration.ofSeconds(10) → Duration.ofSeconds(15)
```

### Issue: "Tests hang/timeout"
```
Solution: Check if app server is responsive
curl http://localhost:3000/books/get-books
```

---

## 📊 Success Indicators

### ✅ All Tests Pass
```
[INFO] Tests run: 15, Failures: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

### ✅ Individual Test Pass
```
testValidSignIn PASSED [took 3.2 seconds]
```

### ✅ Report Generated
```
report generated at: target/surefire-reports/index.html
```

---

## 🎯 Test Execution Order

Tests run in priority order (1-15):
1. ✅ testValidSignIn (priority=1)
2. ✅ testInvalidPassword (priority=2)
3. ✅ testEmptyEmail (priority=3)
... and so on

**Each test is independent** - can run in any order.

---

## 💡 Pro Tips

### Tip 1: Run tests in parallel
```xml
<!-- In testng.xml -->
<suite name="..." parallel="methods" thread-count="5">
```

### Tip 2: Skip slow tests
```bash
mvn test -Dtest=BookstoreLoginTests -Dgroups=fastTests
```

### Tip 3: Create test reports
```bash
mvn clean test site
open target/site/surefire-report.html
```

### Tip 4: Debug single test
```bash
# Run with verbose output
mvn test -Dtest=BookstoreLoginTests#testValidSignIn -X
```

### Tip 5: Save screenshots on failure
```java
// Update @AfterMethod to take screenshot on failure
if (testResult.getStatus() == ITestResult.FAILURE) {
    testUtils.takeScreenshot(testResult.getName());
}
```

---

## 📞 Support Files

**Complete Docs**:
- `AUTOMATION_DOCUMENTATION.md` - Full technical details
- `SELENIUM_TESTS_README.md` - Comprehensive guide
- `QUICKSTART.md` - This file

**Code Files**:
- `src/test/java/pages/PageLogin.java` - All UI interactions
- `src/test/java/base/BaseTest.java` - WebDriver setup
- `src/test/java/tests/BookstoreLoginTests.java` - 15 test cases
- `src/test/java/utils/TestUtils.java` - Helper methods

---

## ✨ You're Ready!

### Next Steps:
1. ✅ Run `mvn clean test`
2. ✅ Check `target/surefire-reports/index.html`
3. ✅ Review test logs
4. ✅ Integrate into CI/CD

---

**Questions?** Check the complete documentation in:
- `AUTOMATION_DOCUMENTATION.md`
- `SELENIUM_TESTS_README.md`

**Version**: 1.0.0 | **Status**: ✅ Production Ready | **Last Updated**: 2026-04-28
