package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import org.junit.Assert;
import org.junit.Test;
import ui.*;
import ui.factories.ArticlePageObjectFactory;
import ui.factories.MyListsPageObjectFactory;
import ui.factories.NavigationUIFactory;
import ui.factories.SearchPageObjectFactory;

public class MyListsTests extends CoreTestCase {
    private static final String name_of_folder = "Test1";
    private static final String login = "test_java_aut", password = "258456Wiki";
    @Test
    @Features(value = {@Feature(value = "List")})
    @DisplayName("Тест сохранения статьи в список")
    @Description("Проверка функционала сохранения статьи в список для чтения")
    @Step("Starting testSaveFirstArticleToList")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testSaveFirstArticleToList() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        NavigationUI NavigationUi = NavigationUIFactory.get(driver);
        MyListsPageObject MyListPageObject = MyListsPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Java (programming language)");
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleName();

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else
        {
            ArticlePageObject.addArticleToMySave();
        }
        if (Platform.getInstance().isMW())
        {
            AutharizationPageObject Auth = new AutharizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            ArticlePageObject.waitForTitleElement();
            Assert.assertEquals("We are not the same page after login", article_title, ArticlePageObject.getArticleName());

            ArticlePageObject.addArticleToMySave();
        }
        ArticlePageObject.closeArticle();
        ArticlePageObject.closeArticle();

        NavigationUi.openNavigation();

        NavigationUi.clickToMyLists();
        if (Platform.getInstance().isAndroid())
        {
            MyListPageObject.openFolderByName(name_of_folder);
            MyListPageObject.swipeByArticleToDelete(article_title);
        }
        if (Platform.getInstance().isMW())
        {driver.navigate().refresh();}
    }
    @Test
    @Features(value = {@Feature(value = "List")})
    @DisplayName("Тест сохранения 2-х статей и удаление")
    @Description("Проверка функционала сохранения статей в список для чтения и удаление одной")
    @Step("Starting testSaveAndDeleteArticlesToLists")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testSaveAndDeleteArticlesToLists()
    {
        SearchPageObject  SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        NavigationUI NavigationUi = NavigationUIFactory.get(driver);
        MyListsPageObject MyListPageObject = MyListsPageObjectFactory.get(driver);

        String seacrh_line = "Java";
        String article_1 = "Java (programming language)";
        String article_2 = "Island in Indonesia";
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(seacrh_line);
        SearchPageObject.clickByArticleWithSubstring(article_1);
        ArticlePageObject.waitForTitleElement();
        String article_title_1 = ArticlePageObject.getArticleName();

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
        Assert.assertEquals("Заголовки статей не совпадают", article_title_2, check_article);


    }
    /*@Test
    public void testSaveAndDeleteArticlesUniversal()
    {
        SearchPageObject  SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        NavigationUI NavigationUi = NavigationUIFactory.get(driver);
        MyListsPageObject MyListPageObject = MyListsPageObjectFactory.get(driver);

        String seacrh_line = "Java";
        String article_1 = "Java (programming language)";
        String article_2 = "Island in Indonesia";

        if (Platform.getInstance().isAndroid())
        {
            SearchPageObject.initSearchInput();
            SearchPageObject.typeSearchLine(seacrh_line);
            SearchPageObject.clickByArticleWithSubstring(article_1);
            ArticlePageObject.waitForTitleElement();
            String article_title_1 = ArticlePageObject.getArticleName();

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
            Assert.assertEquals("Заголовки статей не совпадают", article_title_2, check_article);
        }
        if (Platform.getInstance().isMW())
        {
            SearchPageObject.initSearchInput();
            SearchPageObject.typeSearchLine(seacrh_line);
            SearchPageObject.clickByArticleWithSubstring(article_1);
            ArticlePageObject.waitForTitleElement();
            String article_title_1 = ArticlePageObject.getArticleName();

            AutharizationPageObject Auth = new AutharizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            ArticlePageObject.waitForTitleElement();
            Assert.assertEquals("We are not the same page after login", article_title_1, ArticlePageObject.getArticleName());

            ArticlePageObject.addArticleToMySave();

            SearchPageObject.initSearchInput();
            SearchPageObject.typeSearchLine(seacrh_line);
            SearchPageObject.clickByArticleWithSubstring(article_2);
            ArticlePageObject.waitForTitleElement();
            String article_title_2 = ArticlePageObject.getArticleName();

            ArticlePageObject.addArticleToMySave();

            NavigationUi.openNavigation();
            NavigationUi.clickToMyLists();

            MyListPageObject.swipeByArticleToDelete(article_title_1);
            driver.navigate().refresh();

            String article_check = ArticlePageObject.getArticleName();
            Assert.assertEquals("Сохраненная статья не совпадает с ожидаемой", article_check, article_title_2);
        }
    }*/
}
