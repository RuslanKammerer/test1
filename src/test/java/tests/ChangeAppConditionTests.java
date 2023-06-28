package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import ui.*;
import ui.factories.ArticlePageObjectFactory;
import ui.factories.MyListsPageObjectFactory;
import ui.factories.NavigationUIFactory;
import ui.factories.SearchPageObjectFactory;

public class ChangeAppConditionTests extends CoreTestCase {
    @Test
    @Features(value = {@Feature(value = "AppNavigation")})
    @DisplayName("Тест смены ориентации экрана")
    @Description("Проверка функционала поведения приложения при смене ориентации экрана")
    @Step("Starting testTitleScreenOrientation")
    @Severity(value = SeverityLevel.NORMAL)
    public void testTitleScreenOrientation()
    {
        if (Platform.getInstance().isMW())
        {
            return;
        }
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        NavigationUI NavigationUi = NavigationUIFactory.get(driver);
        MyListsPageObject MyListPageObject = MyListsPageObjectFactory.get(driver);;

        String search_line ="Java";

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring("Java (programming language)");
        String title_decription_wert = ArticlePageObject.getArticleName();

        this.rotateScreenLandscape();

        String title_decription_hor = ArticlePageObject.getArticleName();
        Assert.assertEquals("Описание заголовков не совпадают",title_decription_wert, title_decription_hor );

        this.rotateScreenPortrait();
        String title_decription_second_wert = ArticlePageObject.getArticleName();

        Assert.assertEquals("Описание заголовков не совпадают",title_decription_wert, title_decription_second_wert );
    }
    @Test
    @Features(value = {@Feature(value = "AppNavigation")})
    @DisplayName("Тест бэкграунда")
    @Description("Проверка функционала поведения приложения при отправке и возврат в бекграунд")
    @Step("Starting testArticleCheckandBackgroud")
    @Severity(value = SeverityLevel.MINOR)
    public void testArticleCheckandBackgroud()
    {

        if (Platform.getInstance().isMW())
        {
            return;
        }
        SearchPageObject  SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        MainPageObject MainPageObject = new MainPageObject(driver);
        NavigationUI NavigationUi = NavigationUIFactory.get(driver);
        MyListsPageObject MyListPageObject = MyListsPageObjectFactory.get(driver);

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
