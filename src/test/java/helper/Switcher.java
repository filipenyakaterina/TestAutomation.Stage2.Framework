package helper;

import org.openqa.selenium.WebDriver;

import java.util.Set;

public class Switcher extends Helper {
    private static final String FRAME_NAME = "myFrame";
    private static final int FRAME_NUMBER = 0;

    private static String newTabURL;

    Switcher(WebDriver driver) {
        super(driver);
    }

    public static void openNewTab() {
        Executor.openNewTab();
    }

    private static void switchToTab(Boolean isNew) {
        String currentHandle = driver.getWindowHandle();
        Set<String> handles = driver.getWindowHandles();

        for (String actual : handles) {
            if (!actual.equalsIgnoreCase(currentHandle)) {
                driver.switchTo().window(actual);
                if (isNew) {
                    driver.get(newTabURL);
                }
            }
        }

    }

    public static void switchToOtherTab() {
        switchToTab(false);
    }

    public static void switchToNewTab(String tabURL) {
        newTabURL = tabURL;
        switchToTab(true);
    }

    public static void switchToFrame() {
        driver.switchTo().defaultContent();
        Waiter.waitForFrame(FRAME_NUMBER);
        Waiter.waitForFrame(FRAME_NAME);
    }
}
