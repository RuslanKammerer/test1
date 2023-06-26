package tests;

import lib.CoreTestCase;
import org.junit.Test;
import ui.ArticlePageObject;
import ui.SearchPageObject;

public class ArticleTests extends CoreTestCase {
    @Test
    public void testCompareArticleTitle()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Java (programming language)");
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleName();
        //MainPageObject.waitForElementandClick(By.id("org.wikipedia:id/fragment_onboarding_skip_button"), "не найдена кнопка", 1);
        //MainPageObject.waitForElementandClick(By.id("org.wikipedia:id/search_container"), "не удалось найти id поля поиска", 2);
        //MainPageObject.waitForElementandSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Java", "Не удалось найти java", 1);
        //MainPageObject.waitForElementandClick(By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Java (programming language)']"), "not_find", 3);
        //WebElement title_name = MainPageObject.waitForElementPresent(By.xpath("//*[contains(@text,'Java (programming language)')]"), "Не удалось найти заголовок статьи", 5);
        //String article_title = title_name.getAttribute("text");
        assertEquals("Title is not match", "Java (programming language)", article_title);

    }
    @Test
    public void testSwipeArticle()
    {
        SearchPageObject  SearchPageObject = new SearchPageObject(driver);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticleWithSubstring("Automation for Apps");
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.swipeForFooter();
        //MainPageObject.waitForElementandClick(By.id("org.wikipedia:id/fragment_onboarding_skip_button"), "не найдена кнопка", 1);
        //MainPageObject.waitForElementandClick(By.id("org.wikipedia:id/search_container"), "не удалось найти id поля поиска", 2);
        //MainPageObject.waitForElementandSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Appium", "Не удалось найти java", 1);
        //MainPageObject.waitForElementandClick(By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Automation for Apps']"), "not_find", 3);
        //MainPageObject.waitForElementPresent(By.xpath("//*[contains(@text,'Automation for Apps')]"), "Не удалось найти заголовок статьи", 5);
        //MainPageObject.swipeUpToFindElement(By.xpath("//*[contains(@text,'View article in browser')]"), "Не удалось свайпом найти свайпом что-то", 20);
    }
    @Test
    public void testfindArticlesandClose()
    // В данном тест-кейсе ищется 3 статьи по слову Carbon, стирается строка пропуска и проверяется, что в поле выдачи пусто
    {
        SearchPageObject  SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();

        String search_line = "Carbon";
        SearchPageObject.typeSearchLine(search_line);

        int  amount_of_search_res = SearchPageObject.getAmountOfFindArticles();
        assertTrue("Не найдено достаточное кол-во страниц", amount_of_search_res>3);

        SearchPageObject.waitForCancelBtnAppear();
        SearchPageObject.cliclCancelBtn();
        SearchPageObject.waitForCancelBtnDissapear();
    }
    @Test
    public void testTitlePage()
    {   SearchPageObject  SearchPageObject = new SearchPageObject(driver);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);

        SearchPageObject.initSearchInput();

        String search_line ="Java";
        String article_1 = "Java (programming language)";

        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring(article_1);

        ArticlePageObject.assertNowElementPresent();
    }
}
