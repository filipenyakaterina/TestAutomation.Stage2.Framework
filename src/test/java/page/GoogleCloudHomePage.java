package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.TestLogger;

public class GoogleCloudHomePage extends AbstractPage {

    public static final String HOMEPAGE_URL = "https://cloud.google.com/";

    @FindBy(name = "q")
    private WebElement searchInput;

    public GoogleCloudHomePage(WebDriver driver) {
        super(driver);
    }

    public GoogleCloudHomePage openPage() {
        driver.get(HOMEPAGE_URL);
        TestLogger.writeMessage("Page " + HOMEPAGE_URL + " is opened");
        return this;
    }

    public SearchResultsPage search(String searchQuery) {
        searchInput.click();
        searchInput.sendKeys(searchQuery);
        searchInput.submit();
        TestLogger.writeMessage("Search with search query " + searchQuery + "was executed.");
        return new SearchResultsPage(driver, searchQuery);
    }
}
