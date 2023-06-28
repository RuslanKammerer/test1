package ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

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
            TEST_ELEMENT_PRESENT,
            OPTION_REMOVE_FROM_MY_LIST_BTN;

    public ArticlePageObject(RemoteWebDriver driver)
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
        else if (Platform.getInstance().isIOS()){
            return title_element.getAttribute("name");
        }
        else
        {
            return title_element.getText();
        }
    }
    public void swipeForFooter()
    {
        if (Platform.getInstance().isAndroid())
        {this.swipeUpToFindElement(FOOTER_ELEMENT, "Не удалось свайпнуть к нужному эл-ту",
                20);}
        else if (Platform.getInstance().isIOS())
        {this.swipeUpTillElementAppear(FOOTER_ELEMENT,"Не удалось свайпнуть к нужному эл-ту", 30);}
        else
        {
            this.scrollWebPageTillElementNotVisible(FOOTER_ELEMENT,"cannt find the end of article", 40);
        }
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
    public void addArticleToMySave()
    {
        this.waitForElementandClick(SAVE_BUTTON, "cannt find save btn", 2);
    }

    public void removeArticleFromSavedIfItAdded()
    {
        if (this.isElementPresent(OPTION_REMOVE_FROM_MY_LIST_BTN))
        {
            this.waitForElementandClick(OPTION_REMOVE_FROM_MY_LIST_BTN, "cannot clikc btn to remove article from saved", 2);
            this.waitForElementPresent(SAVE_BUTTON, "cannot find save bitton to lists after removing it");
        }
    }
    public void closeArticle()
    {
        if (Platform.getInstance().isIOS()) {
            this.waitForElementandClick(NAVIGATE_UP_BTN,
                    "Не удалось выйти назад",
                    1);
        } else if (Platform.getInstance().isAndroid()) {
            this.waitForElementandClick(NAVIGATE_UP_BTN,
                    "Не удалось выйти назад",
                    1);
        }
        else
        {
            System.out.println("Method closeArticle does not support on platform" + Platform.getInstance().getPlatformVar());
        }
    }
    public void clickArticleNameInSavedList(String locator, String err_msg, int time_sec)
    {
        this.waitForElementandClick(locator, err_msg, time_sec);
    }

}
