import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class LogInTest {
    WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.ae.com/us/en");
    }

    @AfterEach
    public void teardown() {
        driver.quit();
    }

    @Test
    public void loginTest() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("onetrust-accept-btn-handler"))).click();
        } catch (Exception e) {
            System.out.println("Cookies not displayed");
        }
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("bx-close-inside-2347228"))).click();
        } catch (Exception e) {
            System.out.println("Ad not displayed");
        }

        driver.findElement(By.className("qa-show-sidetray-account")).click();
        driver.findElement(By.cssSelector("button[data-test-btn='signin']")).click();
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("");
        driver.findElement(By.xpath("//button[@data-test-btn=\"login\" and contains(@class, 'btn') and contains(@class, 'btn-primary') and contains(@class, 'qa-btn-login')]")).click();

        String error = driver.findElement(By.xpath("//h6[contains(@class, 'alert-header') and contains(@class, 'alert-danger')]")).getText();
        Assertions.assertNotNull(error);

    }
}

