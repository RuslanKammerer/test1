package tests;

import lib.CoreTestCase;
import org.junit.Test;
import org.openqa.selenium.By;
import ui.*;
import ui.factories.ArticlePageObjectFactory;
import ui.factories.SearchPageObjectFactory;

public class ChangeAppConditionTests extends CoreTestCase {
    @Test
    public void testTitleScreenOrientation()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        NavigationUI NavigationUi = new NavigationUI(driver);
        MyListsPageObject MyListPageObject = new MyListsPageObject(driver);

        String search_line ="Java";

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring("Java (programming language)");
        String title_decription_wert = ArticlePageObject.getArticleName();

        this.rotateScreenLandscape();

        String title_decription_hor = ArticlePageObject.getArticleName();
        assertEquals("Описание заголовков не совпадают",title_decription_wert, title_decription_hor );

        this.rotateScreenPortrait();
        String title_decription_second_wert = ArticlePageObject.getArticleName();

        assertEquals("Описание заголовков не совпадают",title_decription_wert, title_decription_second_wert );
    }
    @Test
    public void testArticleCheckandBackgroud()
    {

        SearchPageObject  SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        MainPageObject MainPageObject = new MainPageObject(driver);
        NavigationUI NavigationUi = new NavigationUI(driver);
        MyListsPageObject MyListPageObject = new MyListsPageObject(driver);

        String search_line ="Java";

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring("Java (programming language)");
        ArticlePageObject.waitForTitleElement();

        this.backgroundApp(2);
        MainPageObject.waitForElementPresent("xpath://*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Java (programming language)']",
                "not_find_article_after_exit_from_background",
                3);
        ArticlePageObject.waitForTitleElement();
    }
}
