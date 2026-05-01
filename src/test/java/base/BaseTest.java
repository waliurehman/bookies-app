package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
    
    protected WebDriver driver;
    
    @BeforeClass
    public void setup() {
        // Setup WebDriver
        WebDriverManager.chromedriver().setup();
        
        // Chrome Options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1280,720");  // Smaller window to save memory
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-plugins");
        options.addArguments("--disable-background-networking");
        options.addArguments("--disable-background-timer-throttling");
        options.addArguments("--disable-breakpad");
        options.addArguments("--disable-client-side-phishing-detection");
        options.addArguments("--disable-default-apps");
        options.addArguments("--disable-hang-monitor");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-prompt-on-repost");
        options.addArguments("--disable-sync");
        options.addArguments("--metrics-recording-only");
        options.addArguments("--mute-audio");
        options.addArguments("--no-first-run");
        options.addArguments("--safebrowsing-disable-auto-update");
        
        // Initialize WebDriver
        driver = new ChromeDriver(options);
        
        // Give Chrome time to fully initialize
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Set implicit wait and page load timeout
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(15));
        driver.manage().timeouts().pageLoadTimeout(java.time.Duration.ofSeconds(30));
    }
    
    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
