package ui.android;

import io.appium.java_client.AppiumDriver;
import ui.NavigationUI;

public class AndroidNavigationUI extends NavigationUI
{
     static {
         NAV_TAB_READING_LISTS = "id:org.wikipedia:id/nav_tab_reading_lists";
     }
     public AndroidNavigationUI(AppiumDriver driver)
     {
         super(driver);
     }
}
