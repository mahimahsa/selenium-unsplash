package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(6));
    }

    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected void type(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }

    protected String getText(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    protected void selectDropdownByText(By locator, String visibleText) {
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        new Select(dropdown).selectByVisibleText(visibleText);
    }

    protected void setCheckbox(By locator, boolean shouldBeChecked) {
        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(locator));
        if (checkbox.isSelected() != shouldBeChecked) {
            checkbox.click();
        }
    }

    // Generate random string
    protected String currentTime() {
        LocalDateTime now = LocalDateTime.now();
        return now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    protected String randomText(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(rand.nextInt(chars.length())));
        }
        return sb.toString();
    }

    // JS Executor
    protected Object runJs(String script, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js.executeScript(script, element);
    }

    protected void hover(WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element).perform();
    }

    public boolean isToastMessageDisplayed(By elementLocator) {
        try {
            WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
            return error.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

}
