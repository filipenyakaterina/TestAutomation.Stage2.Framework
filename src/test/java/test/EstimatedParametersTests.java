package test;

import formatter.ValueFormatter;
import model.Instance;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.GoogleCloudHomePage;
import service.InstanceCreator;

public class EstimatedParametersTests extends CommonConditions {
    @Test(description = "Check field 'VM class' after calculation price at the Google Cloud Platform Pricing Calculator")
    public void checkVMclass() {
        Instance testInstance = InstanceCreator.withCredentialsFromProperty();
        String vmClass = new GoogleCloudHomePage(driver).openPage().
                search(SEARCH_QUERY).followLinkWithSearchResult().selectComputeEngine().
                fillComputeEngineForm(testInstance).clickAddToEstimate().getVmClass();
        Assert.assertTrue(testInstance.getMachineClass().toLowerCase().contains(ValueFormatter.getValueFromString(vmClass.toLowerCase())));
    }

    @Test(description = "Check field 'Instance Type' after calculation price at the Google Cloud Platform Pricing Calculator")
    public void checkInstanceType() {
        Instance testInstance = InstanceCreator.withCredentialsFromProperty();
        String instanceType = new GoogleCloudHomePage(driver).openPage().
                search(SEARCH_QUERY).followLinkWithSearchResult().
                selectComputeEngine().fillComputeEngineForm(testInstance).clickAddToEstimate().getInstanceType();
        Assert.assertTrue(testInstance.getMachineType().toLowerCase().contains(ValueFormatter.getValueFromString(instanceType).toLowerCase()));
    }

    @Test(description = "Check field 'Region' after calculation price at the Google Cloud Platform Pricing Calculator")
    public void checkRegion() {
        Instance testInstance = InstanceCreator.withCredentialsFromProperty();
        String region = new GoogleCloudHomePage(driver).openPage().
                search(SEARCH_QUERY).followLinkWithSearchResult().
                selectComputeEngine().fillComputeEngineForm(testInstance).clickAddToEstimate().getRegion();
        Assert.assertTrue(testInstance.getDatacenterLocation().toLowerCase().contains(ValueFormatter.getValueFromString(region).toLowerCase()));
    }

    @Test(description = "Check field 'Local SSD' after calculation price at the Google Cloud Platform Pricing Calculator")
    public void checkLocalSSD() {
        Instance testInstance = InstanceCreator.withCredentialsFromProperty();
        String localSSD = new GoogleCloudHomePage(driver).openPage().
                search(SEARCH_QUERY).followLinkWithSearchResult().selectComputeEngine().fillComputeEngineForm(testInstance).
                clickAddToEstimate().getLocalSSD();
        Assert.assertTrue(localSSD.toLowerCase().contains(testInstance.getLocalSSD().toLowerCase()));
    }

    @Test(description = "Check field 'Commitment Term' after calculation price at the Google Cloud Platform Pricing Calculator")
    public void checkCommitmentTerm() {
        Instance testInstance = InstanceCreator.withCredentialsFromProperty();
        String commitmentTerm = new GoogleCloudHomePage(driver).openPage().
                search(SEARCH_QUERY).followLinkWithSearchResult().
                selectComputeEngine().fillComputeEngineForm(testInstance).
                clickAddToEstimate().getCommitmentTerm();
        Assert.assertTrue(testInstance.getCommittedUsage().toLowerCase().contains(ValueFormatter.getValueFromString(commitmentTerm).toLowerCase()));
    }
}
