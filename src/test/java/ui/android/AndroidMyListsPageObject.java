package ui.android;

import io.appium.java_client.AppiumDriver;
import ui.MyListsPageObject;

public class AndroidMyListsPageObject extends MyListsPageObject {
    static {
        FOLDER_BY_NAME_TEMPL = "xpath://*[contains(@text,'{FOLDER_NAME}')]";
                ARTICLE_BY_TITLE_TEMPL= "xpath://*[@text='{TITLE}']";
    }
    public AndroidMyListsPageObject(AppiumDriver driver)
    {
        super(driver);
    }

}
