package ui.ios;

import io.appium.java_client.AppiumDriver;
import ui.NavigationUI;

public class IOSNavigationUI extends NavigationUI {
    static {NAV_TAB_READING_LISTS = "id:org.wikipedia:id/nav_tab_reading_lists";}
    public IOSNavigationUI(AppiumDriver driver)
    {
        super(driver);
    }
}
