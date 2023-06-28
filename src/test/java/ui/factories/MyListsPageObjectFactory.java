package ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;
import ui.MyListsPageObject;
import ui.android.AndroidMyListsPageObject;
import ui.ios.IOSMyListsPageObject;
import ui.mobile_web.MWMyListsPageObject;

public class MyListsPageObjectFactory {
    public static MyListsPageObject get(RemoteWebDriver driver)
    {
        if (Platform.getInstance().isAndroid())
        {
            return new AndroidMyListsPageObject(driver);
        }
        else if (Platform.getInstance().isIOS())
        {
            return new IOSMyListsPageObject(driver);
        }
        else
        {
            return new MWMyListsPageObject(driver);
        }
    }
}
