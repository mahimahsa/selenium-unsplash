package pages;

import helpers.Config;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage{

    public HomePage(WebDriver driver) {
        super(driver);
        driver.get(Config.HOME);
    }

}
