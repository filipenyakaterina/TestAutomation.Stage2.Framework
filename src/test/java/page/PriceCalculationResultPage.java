package page;

import helper.Executor;
import helper.Switcher;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.TestLogger;

import java.util.Optional;

public class PriceCalculationResultPage extends AbstractPage {

    @FindBy(xpath = "//md-card-content[@id = 'resultBlock']//div[contains(text(),'VM class')]")
    private WebElement vmClass;

    @FindBy(xpath = "//md-card-content[@id = 'resultBlock']//div[contains(text(),'Instance type')]")
    private WebElement instanceType;

    @FindBy(xpath = "//md-card-content[@id = 'resultBlock']//div[contains(text(),'Region')]")
    private WebElement region;

    @FindBy(xpath = "//md-card-content[@id = 'resultBlock']//div[contains(text(),'Commitment term')]")
    private WebElement commitmentTerm;

    @FindBy(xpath = "//md-card-content[@id='resultBlock']//button[@id='email_quote']")
    private WebElement emailEstimateButton;

    @FindBy(xpath = "//md-card-content[@id='resultBlock']//b[contains(text(), 'Total Estimated Cost:')]")
    private WebElement estimateCost;

    private final By localSSDLocator = By.xpath("//md-card-content[@id = 'resultBlock']//div[contains(text(),'local SSD')]");

    protected PriceCalculationResultPage(WebDriver driver) {
        super(driver);
        if (!driver.getCurrentUrl().contains(GoogleCloudPlatformPricingCalculatorPage.CALCULATOR_URL)) {
            throw new IllegalStateException("This is not the Google Cloud Platform Pricing Calculator page!");
        }
        Switcher.switchToFrame();
    }

    public AbstractPage openPage() {
        throw new RuntimeException("Cannot open page with the result of price calculation without providing data entry to the Google Cloud Platform Pricing Calculator.");
    }

    public String getVmClass() {
        String vmClassValue = vmClass.getText();
        TestLogger.writeMessage("Field VM class on Estimate form has value " + vmClassValue);
        return vmClassValue;
    }

    public String getInstanceType() {
        String instanceTypeValue = instanceType.getText();
        TestLogger.writeMessage("Field Instance Type on Estimate form has value " + instanceTypeValue);
        return instanceTypeValue;
    }

    public String getRegion() {
        String regionValue = region.getText();
        TestLogger.writeMessage("Field Region on Estimate form has value " + regionValue);
        return regionValue;
    }

    public String getLocalSSD() {
        String localSSDValue;

        Optional<WebElement> optionalLocalSSD = driver.findElements(localSSDLocator).stream().findFirst();
        if(optionalLocalSSD.isPresent()){
            localSSDValue = optionalLocalSSD.get().getText();
            TestLogger.writeMessage("Field Local SSD on Estimate form has value " + localSSDValue);
        }
        else {
            localSSDValue = "";
            TestLogger.writeMessage("Field Local SSD on Estimate form is absent");
        }
        return localSSDValue;
    }

    public String getCommitmentTerm() {
        String commitmentTermValue = commitmentTerm.getText();
        TestLogger.writeMessage("Field Commitment Term on Estimate form has value " + commitmentTermValue);
        return commitmentTermValue;
    }

    public EmailEstimatePage clickEmailEstimate() {
        Executor.clickElement(emailEstimateButton);
        TestLogger.writeMessage("Email Estimate button was pressed");
        return new EmailEstimatePage(driver);
    }

    public String getEstimatedCost() {
        String estimateCostValue = estimateCost.getText();
        TestLogger.writeMessage("Estimate result is " + estimateCostValue);
        return estimateCostValue;
    }
}

