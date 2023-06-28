package ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import ui.MyListsPageObject;
import ui.android.AndroidMyListsPageObject;
import ui.ios.IOSMyListsPageObject;

public class MyListsPageObjectFactory {
    public static MyListsPageObject get(AppiumDriver driver)
    {
        if (Platform.getInstance().isAndroid())
        {
            return new AndroidMyListsPageObject(driver);
        }
        else
        {
            return new IOSMyListsPageObject(driver);
        }
    }
}
