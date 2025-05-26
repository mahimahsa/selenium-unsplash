import helper.CookieConsent;
import helper.LoginVerifier;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.*;
import pages.LoginPage;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

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
    public void autoClickCookieConseptPopup(){
        driver.get("https://unsplash.com/login");
        CookieConsent cookieConsent = new CookieConsent(driver);
        cookieConsent.suppressConsentPopup();
        List<WebElement> consentDialog = driver.findElements(By.xpath("//dialog//a[@href='/cookies#manage-consent']"));
        assertTrue( consentDialog.isEmpty());
    }

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
    @Test
    public void userShouldBeRedirectedIfNotLoggedIn() {
        LoginVerifier verifier = new LoginVerifier(driver);
        assertTrue(verifier.isRedirectedToLogin());
    }

    @Test
    public void userShouldBeLoggedInAfterValidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.loginSuccessfully();

        LoginVerifier verifier = new LoginVerifier(driver);
        assertTrue(verifier.isUserLoggedIn());
    }


    @AfterEach
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
