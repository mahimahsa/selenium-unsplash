import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class FirstSeleniumTest {

    WebDriver driver;

    @BeforeEach
    public void setUp() throws MalformedURLException {

        ChromeOptions options = new ChromeOptions();
//        String seleniumUrl = System.getenv("SELENIUM_HOST");
//        if (seleniumUrl == null || seleniumUrl.isEmpty()) {
//            seleniumUrl = "http://localhost:4444/wd/hub";
//        }
//        driver = new RemoteWebDriver(new URL(seleniumUrl), options);
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver(options);
//        driver.manage().window().maximize();
        driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        driver.manage().window().maximize();


    }

    @Test
    public void openHomePage() {
        driver.get("https://unsplash.com");
        System.out.println("Title: " + driver.getTitle());
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
