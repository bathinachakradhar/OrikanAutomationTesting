package Testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.Assert;

public class OrikanAutomationCard {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Set up WebDriver 
    	WebDriverManager.chromedriver().setup();
    	System.setProperty("webdriver.chrome.driver", "C:\\Users\\PC\\.m2\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testRegistrationWithExistingEmail() {
        // Test data
        String username = AppConfig.USERNAME2;
        String password = AppConfig.PASSWORD2;
        String expectedToastMessage = "Email address is already registered";

        // Navigate to the registration page
        driver.get(AppConfig.URL);

        // Fill in registration form
        driver.findElement(Locators.EMAIL_ADDRESS_INPUT).sendKeys(username);
        driver.findElement(Locators.PASSWORD_INPUT).sendKeys(password);
        driver.findElement(Locators.CONFIRM_PASSWORD_INPUT).sendKeys(password);
        driver.findElement(Locators.REGISTER_BUTTON_STEP1).click();

        // Get the toast message
        String actualToastMessage = driver.findElement(By.className("toast-message error active")).getText();

        // Verify toast message
        Assert.assertEquals(actualToastMessage, expectedToastMessage, "Incorrect toast message displayed");
        System.out.println("Test case passed: Registration with existing email detected successfully");
    }

    @AfterMethod
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}
