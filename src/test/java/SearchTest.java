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
import java.util.List;


public class SearchTest {
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
    public void SearchForTest() throws InterruptedException {
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

        driver.findElement(By.className("qa-show-sidetray-search")).click();
        driver.findElement(By.xpath("//input[@name='search']")).sendKeys("1149");
        driver.findElement(By.className("search-btn")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test-product-name=\"\"]")));

        List<WebElement> similarElements = driver.findElements(By.cssSelector("[data-test-product-name=\"\"]"));
        System.out.println(similarElements.size());
        for (WebElement element : similarElements) {
            String elementText = element.getText();
            System.out.println(elementText);
        }

    }
}

