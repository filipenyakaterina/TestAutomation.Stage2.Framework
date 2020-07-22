package test;

import model.Instance;
import formatter.ValueFormatter;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.GoogleCloudHomePage;

public class EstimatedParametersTests extends CommonConditions {
    @Test(description = "Check field 'VM class' after calculation price at the Google Cloud Platform Pricing Calculator",
            dataProvider = "testData")
    public void checkVMclass(Instance testInstance) {
        String vmClass = new GoogleCloudHomePage(driver).openPage().
                search(SEARCH_QUERY).followLinkWithSearchResult().selectComputeEngine().
                fillComputeEngineForm(testInstance).clickAddToEstimate().getVmClass();
        Assert.assertTrue(testInstance.getMachineClass().toLowerCase().contains(ValueFormatter.getValueFromString(vmClass.toLowerCase())));
    }

    @Test(description = "Check field 'Instance Type' after calculation price at the Google Cloud Platform Pricing Calculator",
            dataProvider = "testData")
    public void checkInstanceType(Instance testInstance) {
        String instanceType = new GoogleCloudHomePage(driver).openPage().
                search(SEARCH_QUERY).followLinkWithSearchResult().
                selectComputeEngine().fillComputeEngineForm(testInstance).clickAddToEstimate().getInstanceType();
        Assert.assertTrue(testInstance.getMachineType().toLowerCase().contains(ValueFormatter.getValueFromString(instanceType).toLowerCase()));
    }

    @Test(description = "Check field 'Region' after calculation price at the Google Cloud Platform Pricing Calculator",
            dataProvider = "testData")
    public void checkRegion(Instance testInstance) {
        String region = new GoogleCloudHomePage(driver).openPage().
                search(SEARCH_QUERY).followLinkWithSearchResult().
                selectComputeEngine().fillComputeEngineForm(testInstance).clickAddToEstimate().getRegion();
        Assert.assertTrue(testInstance.getDatacenterLocation().toLowerCase().contains(ValueFormatter.getValueFromString(region).toLowerCase()));
    }

    @Test(description = "Check field 'Local SSD' after calculation price at the Google Cloud Platform Pricing Calculator",
            dataProvider = "testData")
    public void checkLocalSSD(Instance testInstance) {
        String localSSD = new GoogleCloudHomePage(driver).openPage().
                search(SEARCH_QUERY).followLinkWithSearchResult().selectComputeEngine().fillComputeEngineForm(testInstance).
                clickAddToEstimate().getLocalSSD();
        Assert.assertTrue(localSSD.toLowerCase().contains(testInstance.getLocalSSD().toLowerCase()));
    }

    @Test(description = "Check field 'Commitment Term' after calculation price at the Google Cloud Platform Pricing Calculator",
            dataProvider = "testData")
    public void checkCommitmentTerm(Instance testInstance) {
        String commitmentTerm = new GoogleCloudHomePage(driver).openPage().
                search(SEARCH_QUERY).followLinkWithSearchResult().
                selectComputeEngine().fillComputeEngineForm(testInstance).
                clickAddToEstimate().getCommitmentTerm();
        Assert.assertTrue(testInstance.getCommittedUsage().toLowerCase().contains(ValueFormatter.getValueFromString(commitmentTerm).toLowerCase()));
    }
}
