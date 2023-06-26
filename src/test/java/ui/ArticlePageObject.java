package ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject
{
    private static final String
            SUB_TITLE = "id:pcs-edit-section-title-description",
            FOOTER_ELEMENT = "xpath://*[contains(@text,'View article in browser')]",
            SAVE_BUTTON = "id:org.wikipedia:id/page_save",
            SNACKBAR_ACTION = "id:org.wikipedia:id/snackbar_action",
            INPUT_TEXT = "id:org.wikipedia:id/text_input",
            OK_BUTTON = "id:android:id/button1",
            NAVIGATE_UP_BTN = "xpath://android.widget.ImageButton[@content-desc='Navigate up']",
            SAVED_READING_LIST_TMP="xpath://*[contains(@text,'{SAVED_LIST}')]",
            TEST_ELEMENT_PRESENT = "xpath://*[@resource-id='pcs-edit-section-title-description']";

    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }
    private static String getSavedFolderByName(String name_of_folder)
    {
        return SAVED_READING_LIST_TMP.replace("{SAVED_LIST}", name_of_folder);
    }
    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(SUB_TITLE, "Не удалось найти подзаголовок статьи", 5);
    }
    public String getArticleName()
    {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }
    public void swipeForFooter()
    {
        this.swipeUpToFindElement(FOOTER_ELEMENT, "Не удалось свайпнуть к нужному эл-ту", 20);
    }
    public void addArticleToMyList(String name_of_folder)
    {
        this.waitForElementandClick(SAVE_BUTTON,
                "not find save button",
                2);
        this.waitForElementandClick(SNACKBAR_ACTION,
                "not find save button",
                2);

        this.waitForElementandSendKeys(INPUT_TEXT, name_of_folder,
                "Не удалось ввести имя папки для сохранения",
                1);
       this.waitForElementandClick(OK_BUTTON,
                "not find OK button",
                2);
    }
    public void addArticleToSavedList(String saved_list)
    {
        String saved_articles_list = getSavedFolderByName(saved_list);
        this.waitForElementandClick(SAVE_BUTTON,
                "not find save button",
                2);
        this.waitForElementandClick(SNACKBAR_ACTION,
                "not find save button",
                2);
        this.waitForElementandClick(saved_articles_list, "Не выбрать сохраненный список для сохранения", 2);
    }
    public void assertNowElementPresent()
    {
        this.assertElementPresent(TEST_ELEMENT_PRESENT, "Нужный элемент не найден сразу на странице при прогрузке");
    }

    public void closeArticle()
    {
       this.waitForElementandClick(NAVIGATE_UP_BTN,
                "Не удалось выйти назад",
                1);
    }
}
