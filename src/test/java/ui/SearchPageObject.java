package ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class SearchPageObject extends MainPageObject
{
     protected static String
            SKIP_BUTTON,
            CLEAR_BUTTON,
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            SEARCH_RESULT_BY_SAVED_SUBSTRING_TPL,
            SEARCH_RESULT_ELEMENT,
            EMPTY_SEARCH_RES,
             SEARCH_INIT_ELEMENT_MW;

    public SearchPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
    /* Template method */
    @Step("getResultSearchElement")
    private static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    @Step("getResultSearchElementInSavedList")
    private static String getResultSearchElementInSavedList(String substring)
    {
        return SEARCH_RESULT_BY_SAVED_SUBSTRING_TPL.replace("{SUBSTRING_SV}", substring);
    }
    @Step("initSearchInput")
    /* Template method */
    public void initSearchInput()
    {
        if (Platform.getInstance().isAndroid())
        {
            this.waitForElementandClick(SKIP_BUTTON, "Не удалось найти кнопку skip", 1);
            this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find search input after clicking search init element");
            this.waitForElementandClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element", 2);
        }
        if (Platform.getInstance().isMW())
        {
            this.waitForElementandClick(SEARCH_INIT_ELEMENT, "Cannot find seacrh button in mw", 2);
        }

    }
    @Step("waitForCancelBtnAppear")
    public void waitForCancelBtnAppear()
    {
        this.waitForElementPresent(CLEAR_BUTTON, "Не удлаось найти кнопку очистки поля",1);
    }
    @Step("waitForCancelBtnDissapear")
    public void waitForCancelBtnDissapear()
    {
        this.waitForElementNotPresent(CLEAR_BUTTON, "Кнопка очистки поиска еще на месте",1);
    }
    @Step("cliclCancelBtn")
    public void cliclCancelBtn()
    {
        this.waitForElementandClick(CLEAR_BUTTON, "Не удлась нажать на кнопку очистки поиска", 2);
    }
    @Step("typeSearchLine '{search_line}'")
    public void typeSearchLine(String search_line)
    {
        this.waitForElementandSendKeys(SEARCH_INPUT, search_line, "Не удалось отправить значение", 2);
    }
    @Step("waitForSearchResult")
    public void waitForSearchResult(String substring)
    {
        String search_Result_Xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_Result_Xpath, "Не удалось получить результат с строкой " + substring);
    }
    @Step("clickByArticleWithSubstring")
    public void clickByArticleWithSubstring(String substring)
    {
        String search_Result_Xpath = getResultSearchElement(substring);
        this.waitForElementandClick(search_Result_Xpath, "Не удалось кликнуть на статью с подстрокой " + substring, 2);
    }
    @Step("clickByArticleWithSubstringInSavedList")
    public void clickByArticleWithSubstringInSavedList(String substring)
    {
        String search_Result_Xpath_SV = getResultSearchElementInSavedList(substring);
        this.waitForElementandClick(search_Result_Xpath_SV, "Не удалось кликнуть на статью с подстрокой " + substring, 2);
    }
    @Step("getAmountOfFindArticles")
    public int getAmountOfFindArticles()
    {

        this.waitForElementPresent(SEARCH_RESULT_ELEMENT,"Cannot find anything by this request", 3);
        return this.getAmountofElement(SEARCH_RESULT_ELEMENT);

    }
    @Step("waitForEmptyResultLabel")
    public void waitForEmptyResultLabel()
    {
        this.waitForElementPresent(EMPTY_SEARCH_RES, "Результаты поиска не пустые", 3);
    }
    @Step("assertThereIsNoResultSearch")
    public void assertThereIsNoResultSearch()
    {
        this.assertElementNotPresent(SEARCH_RESULT_ELEMENT, "Что-то найдено");
    }
}
