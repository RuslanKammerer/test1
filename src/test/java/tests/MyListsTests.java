package tests;

import lib.CoreTestCase;
import org.junit.Test;
import ui.ArticlePageObject;
import ui.MyListsPageObject;
import ui.NavigationUI;
import ui.SearchPageObject;
import ui.factories.ArticlePageObjectFactory;
import ui.factories.SearchPageObjectFactory;

public class MyListsTests extends CoreTestCase {
    @Test
    public void testSaveFirstArticleToList()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        NavigationUI NavigationUi = new NavigationUI(driver);
        MyListsPageObject MyListPageObject = new MyListsPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Java (programming language)");
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleName();
        String name_of_folder = "Test1";

        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();
        ArticlePageObject.closeArticle();

        NavigationUi.clickToMyLists();

        MyListPageObject.openFolderByName(name_of_folder);
        MyListPageObject.swipeByArticleToDelete(article_title);
    }
    @Test
    public void testSaveAndDeleteArticlesToLists()
    {
        SearchPageObject  SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        NavigationUI NavigationUi = new NavigationUI(driver);
        MyListsPageObject MyListPageObject = new MyListsPageObject(driver);

        String seacrh_line = "Java";
        String article_1 = "Java (programming language)";
        String article_2 = "Island in Indonesia";
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(seacrh_line);
        SearchPageObject.clickByArticleWithSubstring(article_1);
        ArticlePageObject.waitForTitleElement();
        String article_title_1 = ArticlePageObject.getArticleName();
        String name_of_folder = "Test1";

        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();

        SearchPageObject.clickByArticleWithSubstring(article_2);
        ArticlePageObject.waitForTitleElement();
        String article_title_2 = ArticlePageObject.getArticleName();

        ArticlePageObject.addArticleToSavedList(name_of_folder);
        ArticlePageObject.closeArticle();
        ArticlePageObject.closeArticle();

        NavigationUi.clickToMyLists();

        MyListPageObject.openFolderByName(name_of_folder);
        MyListPageObject.swipeByArticleToDelete(article_title_1);

        SearchPageObject.clickByArticleWithSubstringInSavedList(article_2);
        String check_article = ArticlePageObject.getArticleName();
        assertEquals("Заголовки статей не совпадают", article_title_2, check_article);


    }
}
