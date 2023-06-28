package ui;

import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject {
    protected static String
            NAV_TAB_READING_LISTS,
            OPEN_NAVIGATION,
            NAV_TAB_WATCHLIST;


    public NavigationUI(RemoteWebDriver driver)
    {
        super(driver);
    }
    public void clickToMyLists()
    {
        if (Platform.getInstance().isMW()) {
            this.tryClickElementWithFewAttempts(
                    NAV_TAB_WATCHLIST,
                    "Cannot find navigation button to My list",
                    5
            );
        } else {
            this.waitForElementandClick(
                    NAV_TAB_READING_LISTS,
                    "Cannot find navigation button to My list",
                    5
            );
        }
    }
    public void openNavigation()
    {
        if (Platform.getInstance().isMW())
        {
            this.waitForElementandClick(OPEN_NAVIGATION, "cannot click btn navigation", 2);
        }
        else
        {
            System.out.println("Method openNavigation not support on" + Platform.getInstance().getPlatformVar());
        }
    }


}
