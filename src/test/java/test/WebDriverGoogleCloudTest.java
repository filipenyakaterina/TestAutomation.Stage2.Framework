package test;

import data_entity.InstancesData;
import formatter.CostFormatter;
import formatter.ValueFormatter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.EmailEstimatePage;
import page.GoogleCloudHomePage;
import page.MessagesListPage;
import page.TempMailHomePage;

public class WebDriverGoogleCloudTest {
    private static final String SEARCH_QUERY = "Google Cloud Platform Pricing Calculator";

    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @DataProvider
    public Object[][] testData() {
        return new Object[][]{
                new Object[]{new InstancesData("4", "",
                        "Free: Debian, CentOS, CoreOS, Ubuntu, or other User Provided OS", "Regular",
                        "n1-standard-8 (vCPUs: 8, RAM: 30GB)", "1", "NVIDIA Tesla V100",
                        "2x375 GB", "Frankfurt (europe-west3)", "1 Year")},
        };
    }

    @Test(description = "Check field 'VM class' after calculation price at the Google Cloud Platform Pricing Calculator",
            dataProvider = "testData")
    public void checkVMclass(InstancesData testInstancesData) {
        String vmClass = new GoogleCloudHomePage(driver).openPage().
                search(SEARCH_QUERY).followLinkWithSearchResult().selectComputeEngine().
                fillComputeEngineForm(testInstancesData).clickAddToEstimate().getVmClass();
        Assert.assertTrue(testInstancesData.getMachineClass().toLowerCase().contains(ValueFormatter.getValueFromString(vmClass.toLowerCase())));
    }

    @Test(description = "Check field 'Instance Type' after calculation price at the Google Cloud Platform Pricing Calculator",
            dataProvider = "testData")
    public void checkInstanceType(InstancesData testInstancesData) {
        String instanceType = new GoogleCloudHomePage(driver).openPage().
                search(SEARCH_QUERY).followLinkWithSearchResult().
                selectComputeEngine().fillComputeEngineForm(testInstancesData).clickAddToEstimate().getInstanceType();
        Assert.assertTrue(testInstancesData.getMachineType().toLowerCase().contains(ValueFormatter.getValueFromString(instanceType).toLowerCase()));
    }

    @Test(description = "Check field 'Region' after calculation price at the Google Cloud Platform Pricing Calculator",
            dataProvider = "testData")
    public void checkRegion(InstancesData testInstancesData) {
        String region = new GoogleCloudHomePage(driver).openPage().
                search(SEARCH_QUERY).followLinkWithSearchResult().
                selectComputeEngine().fillComputeEngineForm(testInstancesData).clickAddToEstimate().getRegion();
        Assert.assertTrue(testInstancesData.getDatacenterLocation().toLowerCase().contains(ValueFormatter.getValueFromString(region).toLowerCase()));
    }

    @Test(description = "Check field 'Local SSD' after calculation price at the Google Cloud Platform Pricing Calculator",
            dataProvider = "testData")
    public void checkLocalSSD(InstancesData testInstancesData) {
        String localSSD = new GoogleCloudHomePage(driver).openPage().
                search(SEARCH_QUERY).followLinkWithSearchResult().selectComputeEngine().fillComputeEngineForm(testInstancesData).
                clickAddToEstimate().getLocalSSD();
        Assert.assertTrue(localSSD.toLowerCase().contains(testInstancesData.getLocalSSD().toLowerCase()));
    }

    @Test(description = "Check field 'Commitment Term' after calculation price at the Google Cloud Platform Pricing Calculator",
            dataProvider = "testData")
    public void checkCommitmentTerm(InstancesData testInstancesData) {
        String commitmentTerm = new GoogleCloudHomePage(driver).openPage().
                search(SEARCH_QUERY).followLinkWithSearchResult().
                selectComputeEngine().fillComputeEngineForm(testInstancesData).
                clickAddToEstimate().getCommitmentTerm();
        Assert.assertTrue(testInstancesData.getCommittedUsage().toLowerCase().contains(ValueFormatter.getValueFromString(commitmentTerm).toLowerCase()));
    }

    @Test(description = "Check that the value of total estimate cost after the calculation of price with automation test matches with the value after manual test.",
            dataProvider = "testData")
    public void checkTotalEstimateCost(InstancesData testInstancesData) {
        final String TOTAL_ESTIMATE_COST_BY_MANUAL_TEST = "USD 1,082.77 per 1 month";

        String totalEstimateCost = new GoogleCloudHomePage(driver).openPage().
                search(SEARCH_QUERY).followLinkWithSearchResult().
                selectComputeEngine().fillComputeEngineForm(testInstancesData).
                clickAddToEstimate().getTotalEstimateCost();
        Assert.assertTrue(totalEstimateCost.toLowerCase().contains(TOTAL_ESTIMATE_COST_BY_MANUAL_TEST.toLowerCase()));
    }

    @Test(description = "Test verifies that the price calculated by the Google Cloud Platform Pricing Calculator matches the price that was sent by email",
            dataProvider = "testData")
    public void checkEmailEstimate(InstancesData testInstancesData) {
        EmailEstimatePage emailEstimatePage = new GoogleCloudHomePage(driver).openPage().
                search(SEARCH_QUERY).followLinkWithSearchResult().selectComputeEngine().fillComputeEngineForm(testInstancesData).
                clickAddToEstimate().clickEmailEstimate();

        String emailAddress = new TempMailHomePage(driver).openPage().getEmailAddress();
        String costFromCalculator = emailEstimatePage.openPage().sendEmail(emailAddress).getEstimatedCost();
        String costFromEmail = new MessagesListPage(driver).openPage().getEstimateCost();

        Assert.assertEquals(CostFormatter.getCostFromString(costFromCalculator),
                CostFormatter.getCostFromString(costFromEmail));
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }
}
