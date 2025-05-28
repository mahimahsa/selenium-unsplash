package helpers;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

public class CookieConsent {

    protected final WebDriver driver;
    public CookieConsent(WebDriver driver) {
        this.driver = driver;
    }

    //Set fake consent cookie to avoid UI interaction
    public void suppressConsentPopup() {
        Cookie consentCookie = new Cookie(Config.COOKIE_CONSENT, "true");
        driver.manage().addCookie(consentCookie);
        driver.navigate().refresh();
    }
}
