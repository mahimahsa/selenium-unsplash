package pages;

import helpers.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class LoginPage extends BasePage {

    private final By emailField = By.xpath("//input[@name='email']");
    private final By passwordField = By.name("password");
    private final By loginButton = By.xpath("//button[@type='submit' and @value='Login']");
    private final By errorMessage = By.xpath("//p[@class='cPWVz']");

    public LoginPage(WebDriver driver) {
        super(driver);
        driver.get(Config.LOGIN);
    }

    public void loginWithWrongPassword() {
        type(emailField, Config.EMAIL);
        type(passwordField, randomText(10));
        click(loginButton);
    }

    public void loginSuccessfully() {
        type(emailField, Config.EMAIL);
        type(passwordField, Config.PASSWORD);
        click(loginButton);
    }

    public String emailValidationMessage() {
        type(emailField, randomText(10));
        type(passwordField, randomText(10));
        click(loginButton);
        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        return (String) runJs("return arguments[0].validationMessage;", emailInput);
    }

    public boolean isLoginErrorDisplayed() {
        return isToastMessageDisplayed(errorMessage);
    }

}