package pages;

import helpers.Config;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.nio.file.Paths;

public class AccountPage extends BasePage {

        private final By bioTextarea = By.tagName("textarea");
        private final By imageUploadAlert= By.xpath("//div[@class='flash flash--notice animated js-flash js-flash-notice']");
        private final By uploadInput = By.xpath("//input[@type='file' and @id='file']");
        private final By submitButton = By.xpath("//input[@type='submit' and @name='commit']");
        private final By checkBox = By.xpath("//input[@type='checkbox' and @id='allow-messages-checkbox']");
        public AccountPage(WebDriver driver) {
            super(driver);
            driver.get(Config.ACCOUNT);
        }

        public void updateBio() {
            type(bioTextarea, "Bio is updated on: "+currentTime());
            click(submitButton);
        }

        public String readBio() {
            return getText(bioTextarea);
        }

        public boolean getCheckbox() {
            WebElement checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(checkBox));
            return checkbox.isSelected();
        }

        public void toggleCheckbox(boolean checked) {
            setCheckbox(checkBox, !checked);
        }

        public void uploadProfileImage(String filename) {
            String filePath = Paths.get("src", "test", "resources", filename).toAbsolutePath().toString();
            WebElement upload = wait.until(ExpectedConditions.presenceOfElementLocated(uploadInput));
            upload.sendKeys(filePath);
        }

        public boolean isSuccessMessageDisplayed() {
            return isToastMessageDisplayed(imageUploadAlert);
        }

}
