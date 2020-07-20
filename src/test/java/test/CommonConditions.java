package test;

import data_entity.InstancesData;
import driver.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

public class CommonConditions {
    protected static final String SEARCH_QUERY = "Google Cloud Platform Pricing Calculator";

    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        driver = DriverSingleton.getDriver();
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

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        DriverSingleton.closeDriver();
    }
}
