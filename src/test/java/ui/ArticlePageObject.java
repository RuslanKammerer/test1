package ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

abstract public class ArticlePageObject extends MainPageObject
{
    protected static String
            SUB_TITLE,
            FOOTER_ELEMENT,
            SAVE_BUTTON,
            SNACKBAR_ACTION,
            INPUT_TEXT,
            OK_BUTTON,
            NAVIGATE_UP_BTN,
            SAVED_READING_LIST_TMP,
            TEST_ELEMENT_PRESENT;

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
        if (Platform.getInstance().isAndroid())
        {return title_element.getAttribute("text");}
        else {
            return title_element.getAttribute("name");
        }
    }
    public void swipeForFooter()
    {
        if (Platform.getInstance().isAndroid())
        {this.swipeUpToFindElement(FOOTER_ELEMENT, "Не удалось свайпнуть к нужному эл-ту",
                20);}
        else
        {this.swipeUpTillElementAppear(FOOTER_ELEMENT,"Не удалось свайпнуть к нужному эл-ту", 30);}
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
