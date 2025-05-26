
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class LoginPage extends BasePage {

    private final By emailField = By.xpath("//input[@name='email']");
    private final By passwordField = By.name("password");
    private final By loginButton = By.xpath("//button[@type='submit' and @value='Login']");
    private final By errorMessage = By.xpath("//p[@class='cPWVz']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get("https://unsplash.com/login");
    }

    public void loginWithRandomData() {
        type(emailField, randomText(8));
        type(passwordField, randomText(10));
        click(loginButton);
    }

    public void loginWithWrongPassword() {
        type(emailField, "mahsa.daemi.rm@gmail.com");
        type(passwordField, randomText(10));
        click(loginButton);
    }

    public void loginSuccessfully() {
        type(emailField, "mahsa.daemi.rm@gmail.com");
        type(passwordField, "Aa123456");
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
        try {
            WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
            return error.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

}