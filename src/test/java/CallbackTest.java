import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CallbackTest {

        WebDriver driver;

    @BeforeAll
    static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);

        driver = new ChromeDriver(options);
        driver.get("http://localhost:7777/");
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

        @Test
        void validOrder(){
            driver.findElement(By.cssSelector("[data-test-id=\"name\"] input")).sendKeys("Ореолов Евгений");
            driver.findElement(By.cssSelector("[data-test-id=\"phone\"] input")).sendKeys("+79999999999");
            driver.findElement(By.cssSelector("[data-test-id=\"agreement\"]")).click();
            driver.findElement(By.cssSelector("[role=\"button\"]")).click();
            String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
            String actual = driver.findElement(By.cssSelector("[data-test-id=\"order-success\"]")).getText().trim();
            assertEquals (expected, actual);
        }
    }
