package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestUtils {
    
    private WebDriver driver;
    
    public TestUtils(WebDriver driver) {
        this.driver = driver;
    }
    
    /**
     * Take screenshot and save to file
     */
    public void takeScreenshot(String testName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
            String fileName = testName + "_" + timestamp + ".png";
            String filePath = "screenshots/" + fileName;
            
            new File("screenshots").mkdirs();
            File destination = new File(filePath);
            
            FileUtils.copyFile(source, destination);
            System.out.println("Screenshot saved: " + filePath);
        } catch (IOException e) {
            System.out.println("Failed to take screenshot: " + e.getMessage());
        }
    }
    
    /**
     * Wait for a specified time in milliseconds
     */
    public void waitForTime(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    /**
     * Get current page title
     */
    public String getPageTitle() {
        return driver.getTitle();
    }
    
    /**
     * Get current URL
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
    
    /**
     * Refresh page
     */
    public void refreshPage() {
        driver.navigate().refresh();
    }
    
    /**
     * Go back in browser
     */
    public void goBack() {
        driver.navigate().back();
    }
    
    /**
     * Go forward in browser
     */
    public void goForward() {
        driver.navigate().forward();
    }
    
    /**
     * Clear browser cookies
     */
    public void clearCookies() {
        driver.manage().deleteAllCookies();
    }
    
    /**
     * Clear browser cache and cookies
     */
    public void clearCache() {
        clearCookies();
        refreshPage();
    }
    
    /**
     * Get page source
     */
    public String getPageSource() {
        return driver.getPageSource();
    }
    
    /**
     * Switch to new window
     */
    public void switchToNewWindow() {
        String currentHandle = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(currentHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }
    }
    
    /**
     * Close current window
     */
    public void closeCurrentWindow() {
        driver.close();
    }
    
    /**
     * Get number of open windows
     */
    public int getWindowHandlesCount() {
        return driver.getWindowHandles().size();
    }
    
    /**
     * Print test info
     */
    public void printTestInfo(String testName) {
        System.out.println("\n=======================================================");
        System.out.println("TEST: " + testName);
        System.out.println("URL: " + getCurrentUrl());
        System.out.println("Title: " + getPageTitle());
        System.out.println("Timestamp: " + LocalDateTime.now());
        System.out.println("=======================================================\n");
    }
}
