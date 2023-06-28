package ui.android;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import ui.ArticlePageObject;

public class AndroidArticlePageObject extends ArticlePageObject {
    static {
                SUB_TITLE = "id:pcs-edit-section-title-description";
                FOOTER_ELEMENT = "xpath://*[contains(@text,'View article in browser')]";
                SAVE_BUTTON = "id:org.wikipedia:id/page_save";
                SNACKBAR_ACTION = "id:org.wikipedia:id/snackbar_action";
                INPUT_TEXT = "id:org.wikipedia:id/text_input";
                OK_BUTTON = "id:android:id/button1";
                NAVIGATE_UP_BTN = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
                SAVED_READING_LIST_TMP = "xpath://*[contains(@text,'{SAVED_LIST}')]";
                TEST_ELEMENT_PRESENT = "xpath://*[@resource-id='pcs-edit-section-title-description']";
    }
    public AndroidArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
