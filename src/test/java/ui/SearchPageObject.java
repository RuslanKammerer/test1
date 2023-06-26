package ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject
{
    private static final String
            SKIP_BUTTON = "id:org.wikipedia:id/fragment_onboarding_skip_button",
            CLEAR_BUTTON = "id:org.wikipedia:id/search_close_btn",
            SEARCH_INIT_ELEMENT = "xpath://*[contains(@text,'Search Wikipedia')]",
            SEARCH_INPUT = "xpath://*[contains(@text,'Search Wikipedia')]",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='{SUBSTRING}']",
            SEARCH_RESULT_BY_SAVED_SUBSTRING_TPL = "xpath://*[contains(@text,'{SUBSTRING_SV}')]",
            SEARCH_RESULT_ELEMENT= "xpath://*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_title']",
            EMPTY_SEARCH_RES = "xpath://*[contains(@text, 'No results')]";
    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }
    /* Template method */
    private static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    private static String getResultSearchElementInSavedList(String substring)
    {
        return SEARCH_RESULT_BY_SAVED_SUBSTRING_TPL.replace("{SUBSTRING_SV}", substring);
    }
    /* Template method */
    public void initSearchInput()
    {
        this.waitForElementandClick(SKIP_BUTTON, "Не удалось найти кнопку skip", 1);
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find search input after clicking search init element");
        this.waitForElementandClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element", 2);
    }
    public void waitForCancelBtnAppear()
    {
        this.waitForElementPresent(CLEAR_BUTTON, "Не удлаось найти кнопку очистки поля",1);
    }
    public void waitForCancelBtnDissapear()
    {
        this.waitForElementNotPresent(CLEAR_BUTTON, "Кнопка очистки поиска еще на месте",1);
    }
    public void cliclCancelBtn()
    {
        this.waitForElementandClick(CLEAR_BUTTON, "Не удлась нажать на кнопку очистки поиска", 2);
    }
    public void typeSearchLine(String search_line)
    {
        this.waitForElementandSendKeys(SEARCH_INPUT, search_line, "Не удалось отправить значение", 2);
    }
    public void waitForSearchResult(String substring)
    {
        String search_Result_Xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_Result_Xpath, "Не удалось получить результат с строкой " + substring);
    }
    public void clickByArticleWithSubstring(String substring)
    {
        String search_Result_Xpath = getResultSearchElement(substring);
        this.waitForElementandClick(search_Result_Xpath, "Не удалось кликнуть на статью с подстрокой " + substring, 2);
    }
    public void clickByArticleWithSubstringInSavedList(String substring)
    {
        String search_Result_Xpath_SV = getResultSearchElementInSavedList(substring);
        this.waitForElementandClick(search_Result_Xpath_SV, "Не удалось кликнуть на статью с подстрокой " + substring, 2);
    }

    public int getAmountOfFindArticles()
    {

        this.waitForElementPresent(SEARCH_RESULT_ELEMENT,"Cannot find anything by this request", 3);
        return this.getAmountofElement(SEARCH_RESULT_ELEMENT);

    }
    public void waitForEmptyResultLabel()
    {
        this.waitForElementPresent(EMPTY_SEARCH_RES, "Результаты поиска не пустые", 3);
    }
    public void assertThereIsNoResultSearch()
    {
        this.assertElementNotPresent(SEARCH_RESULT_ELEMENT, "Что-то найдено");
    }
}
