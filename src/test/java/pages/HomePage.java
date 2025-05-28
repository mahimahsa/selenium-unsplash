package pages;

import helpers.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage{

    private final By logoutBtnLocator = By.xpath("//span[contains(text(),'Logout')]/parent::button");
    private final By profileMenuBtnLocator = By.xpath("//button[@type='button' and @class='sBmwF aZVYw eJWms']");
    private final By menuItem = By.className("NpNYz");

    public HomePage(WebDriver driver) {
        super(driver);
        driver.get(Config.HOME);
    }
    public String getPageTitle(){
        return driver.getTitle();
    }
    public void logOut(){
        //click(profileMenuBtnLocator);
        WebElement profileMenuBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(profileMenuBtnLocator));
        runJs("arguments[0].click();", profileMenuBtn);
        WebElement logoutBtn = driver.findElement(logoutBtnLocator);
        runJs("arguments[0].click();", logoutBtn);
    }

    public WebElement imageHovered(){
        WebElement hoverTarget = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("figure>div")));
        hover(hoverTarget);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("svg[class='PpK6d']")));
    }

    public void navigateToFirstPhotoAndBack() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("figure a"))).click();
        wait.until(ExpectedConditions.urlContains("/photos/"));
        driver.navigate().back();
        wait.until(ExpectedConditions.urlToBe(Config.HOME));
    }

    public String getMenuItemDisplay(){
        //return value of CSS attribute display for "Get Unsplash+" from header menu
        return driver.findElement(menuItem).getCssValue("display");

//            //check if carousel scroll button (mobile-only) is visible
//            By scrollBtnLocator = By.xpath("//button[@type='button' and @title='scroll list to the right']");
//            boolean isScrollBtnVisible = false;
//            try{
//                List<WebElement> scrollBtns = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(scrollBtnLocator));
//                isScrollBtnVisible = scrollBtns.stream().anyMatch(WebElement::isDisplayed);
//                isScrollBtnVisible = !scrollBtns.isEmpty();
//            }catch (TimeoutException e){
//                isScrollBtnVisible = false;
//            }
    }

}
