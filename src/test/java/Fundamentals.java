import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.jupiter.api.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Fundamentals {
    private WebDriver driver;

    @BeforeAll
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\sbenedick2\\Documents\\IS-296\\296 Week-6\\sb-selenium\\src\\test\\resources\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://www.google.com");
    }

    @Test
    public void testGoogleSearch() {
        WebElement element = driver.findElement(By.name("q"));
        element.clear();
        element.sendKeys("Selenium fundamentals");
        element.submit();
        Duration seconds = Duration.ofSeconds(10);
        new WebDriverWait(driver, seconds).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith("selenium fundamentals");
            }
        });

        assertEquals("Selenium fundamentals - Google Search", driver.getTitle());
    }

    @AfterAll
    public void tearDown() throws Exception {
        // Close browser
        if (driver != null) {
            driver.quit();
        }
    }
}