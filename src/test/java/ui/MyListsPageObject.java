package ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListsPageObject extends MainPageObject
{
    protected static String
            FOLDER_BY_NAME_TEMPL,
            ARTICLE_BY_TITLE_TEMPL,
            REMOVE_FROM_SAVED_BTN;
    public MyListsPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
    @Step("getFolderXpathByName")
    private static String getFolderXpathByName(String name_of_folder)
    {
        return FOLDER_BY_NAME_TEMPL.replace("{FOLDER_NAME}", name_of_folder);
    }
    @Step("getsSaveArticleXpathByTitle")
    private static String getsSaveArticleXpathByTitle(String article_title)
    {
        return ARTICLE_BY_TITLE_TEMPL.replace("{TITLE}", article_title);
    }
    @Step("getRemoveButtonTitle")
    private static String getRemoveButtonTitle(String article_title)
    {
        return REMOVE_FROM_SAVED_BTN.replace("{TITLE}", article_title);
    }
    @Step("openFolderByName")
    public void openFolderByName(String name_of_folder)
    {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementandClick(folder_name_xpath,
                "Не удалось найти сохраненный список статей по имени" + name_of_folder,
                1);
    }
    @Step("swipeByArticleToDelete")
    public void swipeByArticleToDelete(String article_title)
    {
        String article_xpath = getsSaveArticleXpathByTitle(article_title);
        this.waitForArticleToAppearByTitle(article_title);
        this.swipeElementToLeft(article_xpath,
                "Не удалось найти статью для удаления");
        if (Platform.getInstance().isMW())
        {
            String remove_locator = getRemoveButtonTitle(article_title);
            this.waitForElementandClick(remove_locator, "cannot click btn to remove article from saved", 10);
        }
        if (Platform.getInstance().isIOS())
        {
            this.clickElementToTheRightUpperCorner(article_xpath, "cannot_click_to_delete_articel");
        }
        this.waitForArticleDissapearByTitle(article_title);
    }
    @Step("waitForArticleDissapearByTitle")
    public void waitForArticleDissapearByTitle(String article_title)
    {
        String article_xpath = getsSaveArticleXpathByTitle(article_title);
        this.waitForElementNotPresent(article_xpath, "Статья еще на месте" + article_title, 2);
    }
    @Step("waitForArticleToAppearByTitle")
    public void waitForArticleToAppearByTitle(String article_title)
    {
        String article_xpath = getsSaveArticleXpathByTitle(article_title);
        this.waitForElementPresent(article_xpath, "Не могу найти сохраненную статью" + article_title, 2);
    }
}
