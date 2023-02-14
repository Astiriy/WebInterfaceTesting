import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CallbackTest {

        WebDriver driver;

        @BeforeAll
        static void setupAll() {
            System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        }

        @BeforeEach
        void setup() {
            driver = new ChromeDriver();
            driver.get("http://localhost:7777/");
        }

        @AfterEach
        void teardown() {
            driver.quit();
            driver = null;
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
