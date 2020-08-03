package page;

import helper.Executor;
import helper.Switcher;
import helper.Waiter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.TestLogger;

public class MessagesListPage extends AbstractPage {
    private static final String COST_TITLE = "Estimated Monthly Cost:";

    @FindBy(xpath = "//main")
    private WebElement messagesList;

    @FindBy(xpath = "//a[contains(text(), 'Google Cloud Platform Price Estimate')]")
    private WebElement message;

    @FindBy(xpath = "//h2[contains(text(), 'Estimated Monthly Cost:')]")
    private WebElement estimateCost;

    public MessagesListPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public MessagesListPage openPage() {
        Switcher.switchToOtherTab();
        if (!driver.getCurrentUrl().contains(TempMailHomePage.TEMP_MAIL_URL)) {
            throw new IllegalStateException("This is not the Temp-Mail page!");
        }
        return this;
    }

    public String getEstimateCost() {
        Waiter.waitUntilElementToBeVisible(messagesList);
        Executor.scrollToElement(messagesList);
        Waiter.waitUntilElementToBeVisible(message);
        Executor.clickElement(message);
        Waiter.waitUntilTextToBe(estimateCost, COST_TITLE);
        TestLogger.writeMessage("Estimate cost was received from the email message.");
        return estimateCost.getText();
    }
}
