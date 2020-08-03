package page;

import helper.Executor;
import helper.Switcher;
import helper.Waiter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.TestLogger;

public class GoogleCloudPlatformPricingCalculatorPage extends AbstractPage {
    public static final String CALCULATOR_URL = "https://cloud.google.com/products/calculator";

    @FindBy(xpath = "//md-tab-item[1]")
    private WebElement computeEngine;

    @FindBy(xpath = "//button[@aria-label='Add to Estimate']")
    private WebElement addToEstimateButton;

    public GoogleCloudPlatformPricingCalculatorPage(WebDriver driver) {
        super(driver);
        Switcher.switchToFrame();
    }

    public GoogleCloudPlatformPricingCalculatorPage openPage() {
        driver.get(CALCULATOR_URL);
        return this;
    }

    public ComputeEnginePage selectComputeEngine() {
        Waiter.waitUntilElementToBeVisible(computeEngine);
        Executor.clickElement(computeEngine);
        TestLogger.writeMessage("Compute Engine tab was selected.");
        return new ComputeEnginePage(driver);
    }

    public PriceCalculationResult clickAddToEstimate() {
        Executor.clickElement(addToEstimateButton);
        TestLogger.writeMessage("Add To Estimate button was pressed.");
        return new PriceCalculationResult(driver);
    }
}
