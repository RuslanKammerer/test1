package tests;

import lib.CoreTestCase;
import org.junit.Test;
import ui.SearchPageObject;
import ui.factories.SearchPageObjectFactory;

public class SearchTests extends CoreTestCase {
    @Test
    public void testSearch() throws Exception {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }
    @Test
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
    public void testAmountOfNotEmptySearch()
    {
        SearchPageObject  SearchPageObject = SearchPageObjectFactory.get(driver);

        String search_line ="Linkin park discography";

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);

        int  amount_of_search_res = SearchPageObject.getAmountOfFindArticles();
        assertTrue("Страницы не найдены", amount_of_search_res>0);

    }
    @Test
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
