package test;

import data_entity.InstancesData;
import formatter.ValueFormatter;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.GoogleCloudHomePage;

public class EstimatedParametersTests extends CommonConditions {
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
}
