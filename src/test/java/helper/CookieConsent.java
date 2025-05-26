package helper;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

public class CookieConsent {

    protected final WebDriver driver;
    public CookieConsent(WebDriver driver) {
        this.driver = driver;
        driver.get("https://unsplash.com/login");
    }

    //Set fake consent cookie to avoid UI interaction
    public void suppressConsentPopup() {
        Cookie consentCookie = new Cookie("cookie_consent", "true");
        driver.manage().addCookie(consentCookie);
        driver.navigate().refresh();
    }
}
