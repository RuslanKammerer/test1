package ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

abstract public class NavigationUI extends MainPageObject {
    protected static String
            NAV_TAB_READING_LISTS;

    public NavigationUI(AppiumDriver driver)
    {
        super(driver);
    }
    public void clickToMyLists()
    {
        this.waitForElementandClick(NAV_TAB_READING_LISTS,
                "not find navigation button",
                2);
    }

}
