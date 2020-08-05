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
        Assert.assertEquals(testInstance.getMachineClass().toLowerCase(), ValueFormatter.getValueFromString(vmClass.toLowerCase()));
    }

    @Test(description = "Check field 'Instance Type' after calculation price at the Google Cloud Platform Pricing Calculator")
    public void checkInstanceType() {
        Instance testInstance = InstanceCreator.withCredentialsFromProperty();
        String instanceType = new GoogleCloudHomePage(driver).openPage().
                search(SEARCH_QUERY).followLinkWithSearchResult().
                selectComputeEngine().fillComputeEngineForm(testInstance).clickAddToEstimate().getInstanceType();
        Assert.assertEquals(ValueFormatter.getFirstValueInLine(testInstance.getMachineType()).toLowerCase(),
                ValueFormatter.getValueFromString(instanceType).toLowerCase());
    }

    @Test(description = "Check field 'Region' after calculation price at the Google Cloud Platform Pricing Calculator")
    public void checkRegion() {
        Instance testInstance = InstanceCreator.withCredentialsFromProperty();
        String region = new GoogleCloudHomePage(driver).openPage().
                search(SEARCH_QUERY).followLinkWithSearchResult().
                selectComputeEngine().fillComputeEngineForm(testInstance).clickAddToEstimate().getRegion();
        Assert.assertEquals(ValueFormatter.getFirstValueInLine(testInstance.getDatacenterLocation()).toLowerCase(),
                ValueFormatter.getValueFromString(region).toLowerCase());
    }

    @Test(description = "Check field 'Local SSD' after calculation price at the Google Cloud Platform Pricing Calculator")
    public void checkLocalSSD() {
        Instance testInstance = InstanceCreator.withCredentialsFromProperty();
        String localSSD = new GoogleCloudHomePage(driver).openPage().
                search(SEARCH_QUERY).followLinkWithSearchResult().selectComputeEngine().fillComputeEngineForm(testInstance).
                clickAddToEstimate().getLocalSSD();
        Assert.assertEquals(ValueFormatter.getLocalSSDValue(localSSD.toLowerCase()),
                ValueFormatter.getLocalSSDValue(testInstance.getLocalSSD().toLowerCase()));
    }

    @Test(description = "Check field 'Commitment Term' after calculation price at the Google Cloud Platform Pricing Calculator")
    public void checkCommitmentTerm() {
        Instance testInstance = InstanceCreator.withCredentialsFromProperty();
        String commitmentTerm = new GoogleCloudHomePage(driver).openPage().
                search(SEARCH_QUERY).followLinkWithSearchResult().
                selectComputeEngine().fillComputeEngineForm(testInstance).
                clickAddToEstimate().getCommitmentTerm();
        Assert.assertEquals(testInstance.getCommittedUsage().toLowerCase(),
                ValueFormatter.getValueFromString(commitmentTerm).toLowerCase());
    }
}
