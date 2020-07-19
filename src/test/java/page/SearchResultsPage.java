package page;

import helper.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchResultsPage extends AbstractPage {
    private final String searchQuery;

    protected SearchResultsPage(WebDriver driver, String searchQuery) {
        super(driver);
        if (!driver.getCurrentUrl().contains(GoogleCloudHomePage.HOMEPAGE_URL)) {
            throw new IllegalStateException("This is not the Google Cloud page!");
        }
        this.searchQuery = searchQuery;
    }

    public AbstractPage openPage() {
        throw new RuntimeException("Cannot open search page without searching.");
    }

    public GoogleCloudPlatformPricingCalculatorPage followLinkWithSearchResult() {
        Waiter.waitUntilElementAppears(By.linkText(searchQuery));
        driver.findElement(By.linkText(searchQuery)).click();
        return new GoogleCloudPlatformPricingCalculatorPage(driver);
    }
}
