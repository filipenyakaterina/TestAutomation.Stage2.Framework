package page;

import helper.Executor;
import helper.Switcher;
import helper.Waiter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.TestLogger;

public class TempMailHomePage extends AbstractPage {
    public static final String TEMP_MAIL_URL = "https://temp-mail.org";

    @FindBy(xpath = "//input[@id = 'mail']")
    private WebElement emailAddress;

    public TempMailHomePage(WebDriver driver) {
        super(driver);
    }

    public TempMailHomePage openPage() {
        Switcher.openNewTab();
        Waiter.waitUntilNewWindowHandleAppear();
        Switcher.switchToNewTab(TEMP_MAIL_URL);
        TestLogger.writeMessage("Page " + TEMP_MAIL_URL + " is opened");
        return this;
    }

    public String getEmailAddress() {
        Waiter.waitUntilElementToBeVisible(emailAddress);
        Executor.scrollToElement(emailAddress);
        Waiter.waitUntilEmailInValueAppears(emailAddress);
        TestLogger.writeMessage("Temp email address was received on " + TEMP_MAIL_URL);
        return emailAddress.getAttribute("value");
    }
}
