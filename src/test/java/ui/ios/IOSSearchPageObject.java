package ui.ios;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import ui.SearchPageObject;

public class IOSSearchPageObject extends SearchPageObject {
    static {
        SKIP_BUTTON = "id:org.wikipedia:id/fragment_onboarding_skip_button";
        CLEAR_BUTTON = "id:org.wikipedia:id/search_close_btn";
        SEARCH_INIT_ELEMENT = "xpath://*[contains(@text,'Search Wikipedia')]";
        SEARCH_INPUT = "xpath://*[contains(@text,'Search Wikipedia')]";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='{SUBSTRING}']";
        SEARCH_RESULT_BY_SAVED_SUBSTRING_TPL = "xpath://*[contains(@text,'{SUBSTRING_SV}')]";
        SEARCH_RESULT_ELEMENT= "xpath://*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_title']";
        EMPTY_SEARCH_RES = "xpath://*[contains(@text, 'No results')]";
    }
    public IOSSearchPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
