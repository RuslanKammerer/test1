package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import org.junit.Assert;
import org.junit.Test;
import ui.SearchPageObject;
import ui.factories.SearchPageObjectFactory;

public class SearchTests extends CoreTestCase {
    @Test
    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Поиск статьи")
    @Description("Проверка функционала поиска статьи")
    @Step("Starting testSearch")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testSearch() throws Exception {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }
    @Test
    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Отмена поиска")
    @Description("Проверка функционала отмены поиска")
    @Step("Starting testCancelSearch")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCancelSearch()
    {
        SearchPageObject  SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForCancelBtnAppear();
        SearchPageObject.cliclCancelBtn();
        SearchPageObject.waitForCancelBtnDissapear();
    }
    @Test
    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Тест поиска выдачи статей")
    @Description("Проверка функционала выдачи нескольких статей в поиске")
    @Step("Starting testAmountOfNotEmptySearch")
    @Severity(value = SeverityLevel.NORMAL)
    public void testAmountOfNotEmptySearch()
    {
        SearchPageObject  SearchPageObject = SearchPageObjectFactory.get(driver);

        String search_line ="Linkin park discography";

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);

        int  amount_of_search_res = SearchPageObject.getAmountOfFindArticles();
        Assert.assertTrue("Страницы не найдены", amount_of_search_res>0);

    }
    @Test
    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Тест пустого поиска")
    @Description("Проверка функционала поиска когда задано невалидное слово")
    @Step("Starting testAmountOfNotEmptySearch")
    @Severity(value = SeverityLevel.MINOR)
    public void testAmountofEmptySearch()
    {
        SearchPageObject  SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();

        String search_line = "blablablablabla";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultLabel();
        SearchPageObject.assertThereIsNoResultSearch();

    }
}
