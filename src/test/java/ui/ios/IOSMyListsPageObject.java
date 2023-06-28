package ui.ios;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import ui.MyListsPageObject;

public class IOSMyListsPageObject extends MyListsPageObject {
    static {
        ARTICLE_BY_TITLE_TEMPL= "xpath://*[@text='{TITLE}']";
    }
    public IOSMyListsPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
