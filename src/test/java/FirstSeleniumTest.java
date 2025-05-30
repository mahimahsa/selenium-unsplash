import helpers.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.AccountPage;
import pages.HomePage;
import pages.LoginPage;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
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

    @Test
    public void openHomePage() {
        driver.get("https://unsplash.com");
        System.out.println("Title: " + driver.getTitle());
    }
    @Test
    public void autoClickCookieConseptPopup(){
        driver.get(Config.LOGIN);
        CookieConsent cookieConsent = new CookieConsent(driver);
        cookieConsent.suppressConsentPopup();
        List<WebElement> consentDialog = driver.findElements(By.xpath("//dialog//a[@href='/cookies#manage-consent']"));
        assertTrue( consentDialog.isEmpty());
    }

//TEST LOGIN PROCESS: Test input validation and error handling in failed login on login page,
//                    Test successful login with a valid credentials, Test being added userId to cookie,
//                    Test redirecting to login page when a not-login user is in a protected page

    @Test
    @DisplayName("Login email validation shows native browser message")
    public void shouldShowEmailValidationMessage() {
        LoginPage loginPage = new LoginPage(driver);
        String message = loginPage.emailValidationMessage();
        System.out.println("Validation message: " + message);
        assertTrue(message.contains("Please include an '@'"));
    }

    @Test
    public void shouldShowErrorForWrongPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithWrongPassword();
        assertTrue(loginPage.isLoginErrorDisplayed());
    }

    @Test
    public void shouldLoginSuccessfully() {
        LoginPage loginPage = new LoginPage(driver);
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
        loginPage.loginSuccessfully();

        LoginVerifier verifier = new LoginVerifier(driver);
        assertTrue(verifier.isUserLoggedIn());
    }
//// END LOGIN TEST ///////////////////////////////////////



// TEST HOME PAGE FUNCTIONALITY AND RESPONSIVENESS/////////

    @Test
    @DisplayName("Home page title contains 'Unsplash'")
    public void shouldReadHomePageTitle(){
        HomePage homePage = new HomePage(driver);
        assertTrue(homePage.getPageTitle().contains("Unsplash"));
    }

    @ParameterizedTest
    @EnumSource(ViewMode.class)
    @DisplayName("WebDriver configuration: Header layout and content differs between desktop and mobile")
    void headerShouldChangeBetweenScreenModes(ViewMode mode) {
        WebDriverManager.chromedriver().setup();
        //initial a new instance of ChromeViewMode once by mobile options and then by start-maximized as desktop view
        WebDriver localDriver = new ChromeDriver(ChromeViewMode.getMobileOptions(mode));

        try {
            HomePage homePage = new HomePage(localDriver);
            if (mode == ViewMode.DESKTOP) {
                assertEquals("flex", homePage.getMenuItemDisplay());
            } else {
                assertEquals("none", homePage.getMenuItemDisplay());
            }
        } finally {
            localDriver.quit();
        }
    }

    @Test
    @DisplayName("Logout and check being logout")
    public void logoutByButtonClick() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginSuccessfully();

        HomePage homePage = new HomePage(driver);
        homePage.logOut();

        LoginVerifier loginVerifier = new LoginVerifier(driver);
        assertFalse(loginVerifier.isUserLoggedIn());
    }

    @Test
    @DisplayName("Hovers over the first image and checks if the like button appears")
    public void hoverTest() {
        HomePage homePage = new HomePage(driver);
        assertTrue(homePage.imageHovered().isDisplayed(), "Like button should appear on hover");
    }

    @Test
    @DisplayName("History test: Navigate to the page of first photo and back to home page by browser history")
    void shouldNavigateToPhotoAndReturn() {
        HomePage homePage = new HomePage(driver);
        homePage.navigateToFirstPhotoAndBack();
        assertEquals(Config.HOME, driver.getCurrentUrl());
    }
////// END HOME PAGE TEST ///////////////////////////////////


/// Test file upload and fill textarea on account page///////
        @Test
        @DisplayName("Bio textbox and Messaging checkbox should be updated on Account page")
        void shouldUpdateBioAndMessagingCheckbox() {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.loginSuccessfully();

            AccountPage accountPage = new AccountPage(driver);

            //get the current values to check their difference after the update
            String oldBio = accountPage.readBio();
            boolean oldChecked = accountPage.getCheckbox();
            accountPage.toggleCheckbox(oldChecked);

            accountPage.updateBio();

            String newBio = accountPage.readBio();
            boolean newChecked = accountPage.getCheckbox();

            assertAll(
                    ()->assertNotEquals(oldBio, newBio, "Bio content should be different after update"),
                    ()->assertNotEquals(oldChecked, newChecked, "Messaging checked should be different after update")
            );
        }
        @Test
        @DisplayName("Profile image should be updated on Account page")
        void shouldUpdateProfileImage() {

            LoginPage loginPage = new LoginPage(driver);
            loginPage.loginSuccessfully();

            AccountPage accountPage = new AccountPage(driver);
            accountPage.uploadProfileImage(Config.PROFILE_IMAGE);

            assertTrue(accountPage.isSuccessMessageDisplayed(), "The Success message should be displayed");
        }
////// END ACCOUNT PAGE TEST /////////////////////////////////



    @AfterEach
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
