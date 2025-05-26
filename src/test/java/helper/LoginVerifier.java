
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LoginVerifier {
    private final WebDriver driver;

    private final By profileLink = By.xpath("//a[starts-with(@href, '/@') and contains(text(), 'View profile')]");
    private final String protectedPageUrl = "https://unsplash.com/account";
    private final String loginPageUrl = "https://unsplash.com/login";

    public LoginVerifier(WebDriver driver) {
        this.driver = driver;
    }

    //Check if profile link is visible OR uuid cookie exists
    public boolean isUserLoggedIn() {
        Cookie uuidCookie = driver.manage().getCookieNamed("uuid");
        if (uuidCookie != null) return true;

        List<WebElement> profileElements = driver.findElements(profileLink);
        return !profileElements.isEmpty() && profileElements.get(0).isDisplayed();
    }

    //Clear cookies and try accessing protected page
    public boolean isRedirectedToLogin() {
        driver.manage().deleteAllCookies();
        driver.get(protectedPageUrl);
        driver.navigate().refresh();
        return driver.getCurrentUrl().contains("/login");
    }

}

