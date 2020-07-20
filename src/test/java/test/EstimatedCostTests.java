package test;

import data_entity.InstancesData;
import formatter.CostFormatter;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.EmailEstimatePage;
import page.GoogleCloudHomePage;
import page.MessagesListPage;
import page.TempMailHomePage;

public class EstimatedCostTests extends CommonConditions {
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
}
