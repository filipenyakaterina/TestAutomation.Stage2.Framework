package test;

import formatter.CostFormatter;
import model.Instance;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.EmailEstimatePage;
import page.GoogleCloudHomePage;
import page.MessagesListPage;
import page.TempMailHomePage;
import service.InstanceCreator;

public class EstimatedCostTests extends CommonConditions {
    @Test(description = "Check that the value of total estimate cost after " +
            "the calculation of price with automation test matches with the value after manual test.")
    public void checkTotalEstimateCost() {
        Instance testInstance = InstanceCreator.withCredentialsFromProperty();
        String totalEstimateCost = new GoogleCloudHomePage(driver).openPage().
                search(SEARCH_QUERY).followLinkWithSearchResult().
                selectComputeEngine().fillComputeEngineForm(testInstance).
                clickAddToEstimate().getTotalEstimateCost();
        Assert.assertTrue(totalEstimateCost.toLowerCase().contains(testInstance.getEstimateCost().toLowerCase()));
    }

    @Test(description = "Test verifies that the price calculated by the Google Cloud Platform Pricing Calculator " +
            "matches the price that was sent by email")
    public void checkEmailEstimateCost() {
        Instance testInstance = InstanceCreator.withoutEstimateCost();

        EmailEstimatePage emailEstimatePage = new GoogleCloudHomePage(driver).openPage().
                search(SEARCH_QUERY).followLinkWithSearchResult().selectComputeEngine().fillComputeEngineForm(testInstance).
                clickAddToEstimate().clickEmailEstimate();

        String emailAddress = new TempMailHomePage(driver).openPage().getEmailAddress();
        String costFromCalculator = emailEstimatePage.openPage().sendEmail(emailAddress).getEstimatedCost();
        String costFromEmail = new MessagesListPage(driver).openPage().getEstimateCost();

        Assert.assertEquals(CostFormatter.getCostFromString(costFromCalculator),
                CostFormatter.getCostFromString(costFromEmail));
    }
}
