package page;

import model.Instance;
import helper.Executor;
import helper.Waiter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import page_element.MdSelect;

public class ComputeEnginePage extends AbstractPage {

    @FindBy(xpath = "//input[@name = 'quantity']")
    private WebElement numberOfInstances;

    @FindBy(xpath = "//label[text()='What are these instances for?']//following::input[1]")
    private WebElement purposeOfUse;

    @FindBy(xpath = "//label[text()='Operating System / Software']//following::md-select[1]")
    private WebElement operatingSystem;

    @FindBy(xpath = "//label[text()='Machine Class']//following::md-select[1]")
    private WebElement machineClass;

    @FindBy(xpath = "//label[text()='Machine type']//following::md-select[1]")
    private WebElement machineType;

    @FindBy(xpath = "//md-input-container/md-checkbox")
    private WebElement addGPUs;

    @FindBy(xpath = "//label[text()='Number of GPUs']//following::md-select[1]")
    private WebElement numberOfGPUs;

    @FindBy(xpath = "//label[text()='GPU type']//following::md-select[1]")
    private WebElement typeOfGPUs;

    @FindBy(xpath = "//label[text()='Local SSD']//following::md-select[1]")
    private WebElement localSSD;

    @FindBy(xpath = "//label[text()='Datacenter location']//following::md-select[1]")
    private WebElement datacenterLocation;

    @FindBy(xpath = "//label[text()='Committed usage']//following::md-select[1]")
    private WebElement committedUsage;

    public ComputeEnginePage(WebDriver driver) {
        super(driver);
        if (!driver.getCurrentUrl().contains(GoogleCloudPlatformPricingCalculatorPage.CALCULATOR_URL)) {
            throw new IllegalStateException("This is not the Google Cloud Platform Pricing Calculator page!");
        }
    }

    @Override
    public AbstractPage openPage() {
        throw new RuntimeException("Cannot open compute engine page without selection Compute Engine tab.");
    }

    public GoogleCloudPlatformPricingCalculatorPage fillComputeEngineForm(Instance instance) {
        numberOfInstances.sendKeys(instance.getNumberOfInstances());
        purposeOfUse.sendKeys(instance.getPurposeOfUse());

        new MdSelect(operatingSystem).selectByValue(instance.getOperatingSystem());
        new MdSelect(machineClass).selectByValue(instance.getMachineClass());
        new MdSelect(machineType).selectByValue(instance.getMachineType());

        Executor.clickElement(addGPUs);
        Waiter.waitUntilElementToBeVisible(numberOfGPUs);
        new MdSelect(numberOfGPUs).selectByValue(instance.getNumberOfGPUs());
        new MdSelect(typeOfGPUs).selectByValue(instance.getTypeOfGPUs());

        new MdSelect(localSSD).selectByValue(instance.getLocalSSD());
        new MdSelect(datacenterLocation).selectByValue(instance.getDatacenterLocation());
        new MdSelect(committedUsage).selectByValue(instance.getCommittedUsage());

        return new GoogleCloudPlatformPricingCalculatorPage(driver);
    }
}
