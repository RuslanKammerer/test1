package ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;

abstract public class MyListsPageObject extends MainPageObject
{
    protected static String
            FOLDER_BY_NAME_TEMPL,
            ARTICLE_BY_TITLE_TEMPL;
    public MyListsPageObject(AppiumDriver driver)
    {
        super(driver);
    }
    private static String getFolderXpathByName(String name_of_folder)
    {
        return FOLDER_BY_NAME_TEMPL.replace("{FOLDER_NAME}", name_of_folder);
    }
    private static String getsSaveArticleXpathByTitle(String article_title)
    {
        return ARTICLE_BY_TITLE_TEMPL.replace("{TITLE}", article_title);
    }
    public void openFolderByName(String name_of_folder)
    {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementandClick(folder_name_xpath,
                "Не удалось найти сохраненный список статей по имени" + name_of_folder,
                1);
    }
    public void swipeByArticleToDelete(String article_title)
    {
        String article_xpath = getsSaveArticleXpathByTitle(article_title);
        this.waitForArticleToAppearByTitle(article_title);
        this.swipeElementToLeft(article_xpath,
                "Не удалось найти статью для удаления");
        if (Platform.getInstance().isIOS())
        {
            this.clickElementToTheRightUpperCorner(article_xpath, "cannot_click_to_delete_articel");
        }
        this.waitForArticleDissapearByTitle(article_title);
    }
    public void waitForArticleDissapearByTitle(String article_title)
    {
        String article_xpath = getsSaveArticleXpathByTitle(article_title);
        this.waitForElementNotPresent(article_xpath, "Статья еще на месте" + article_title, 2);
    }
    public void waitForArticleToAppearByTitle(String article_title)
    {
        String article_xpath = getsSaveArticleXpathByTitle(article_title);
        this.waitForElementPresent(article_xpath, "Не могу найти сохраненную статью" + article_title, 2);
    }
}
