package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import org.junit.Assert;
import org.junit.Test;
import ui.ArticlePageObject;
import ui.SearchPageObject;
import ui.factories.ArticlePageObjectFactory;
import ui.factories.SearchPageObjectFactory;
@Epic("Tests for articles")
public class ArticleTests extends CoreTestCase {
    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article")})
    @DisplayName("Тест сравнения заголовка статьи с целеым")
    @Description("Открываем статью и убеждаемся что заголов, который ищется, совпадает с целевым")
    @Step("Starting test CompareArticle")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testCompareArticleTitle()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Java (programming language)");
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleName();
        //ArticlePageObject.takeScreenshot("Article_compare");
        Assert.assertEquals("Title is not match", "Object-oriented programming language", article_title);

    }
    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article")})
    @DisplayName("Тест промотки статьи до конца")
    @Description("Проверка функционала промотки статьи до нижнего элемента")
    @Step("Starting test testSwipeArticle")
    @Severity(value = SeverityLevel.MINOR)
    public void testSwipeArticle()
    {
        SearchPageObject  SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticleWithSubstring("Automation for Apps");
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.swipeForFooter();
    }
    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article")})
    @DisplayName("Тест выдачи кол-ва рез-ов поиска")
    @Description("Проверка выдачи кол-ва статей по общему слову в поиске")
    @Severity(value = SeverityLevel.NORMAL)
    public void testfindArticlesandClose()
    {
        SearchPageObject  SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();

        String search_line = "Carbon";
        SearchPageObject.typeSearchLine(search_line);

        int  amount_of_search_res = SearchPageObject.getAmountOfFindArticles();
        Assert.assertTrue("Не найдено достаточное кол-во страниц", amount_of_search_res>3);

        SearchPageObject.waitForCancelBtnAppear();
        SearchPageObject.cliclCancelBtn();
        SearchPageObject.waitForCancelBtnDissapear();
    }
    @Test
    @DisplayName("Падающий тест")
    @Description("Тест падает из-за того, что он сразу ищет заголовок, который еще не загрузился")
    @Features(value = {@Feature(value = "Article")})
    @Severity(value = SeverityLevel.MINOR)
    public void testTitlePage()
    {   SearchPageObject  SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();

        String search_line ="Java";
        String article_1 = "Java (programming language)";

        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring(article_1);

        ArticlePageObject.assertNowElementPresent();
    }
}
