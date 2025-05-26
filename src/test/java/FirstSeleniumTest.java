import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.*;
import java.net.MalformedURLException;
import java.net.URL;

public class FirstSeleniumTest {

    WebDriver driver;

    @BeforeEach
    public void setUp() throws MalformedURLException {

        ChromeOptions options = new ChromeOptions();
        String seleniumUrl = System.getenv("SELENIUM_HOST");

        if (seleniumUrl != null && !seleniumUrl.isEmpty()) {
            driver = new RemoteWebDriver(new URL(seleniumUrl), options);
        } else {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(options);
        }
        driver.manage().window().maximize();
    }

//    @Test
//    public void openHomePage() {
//        driver.get("https://unsplash.com");
//        System.out.println("Title: " + driver.getTitle());
//    }

    @Test
    public void shouldShowEmailValidationMessage() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();

        String message = loginPage.emailValidationMessage();
        System.out.println("Validation message: " + message);

        assertTrue(message.contains("Please include an '@'"));
    }

    @Test
    public void shouldShowErrorForWrongPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.loginWithWrongPassword();
        assertTrue(loginPage.isLoginErrorDisplayed());
    }

    @Test
    public void shouldLoginSuccessfully() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.loginSuccessfully();
        assertFalse(loginPage.isLoginErrorDisplayed());
    }


    @AfterEach
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
