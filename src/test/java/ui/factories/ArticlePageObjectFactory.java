package ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import ui.ArticlePageObject;
import ui.android.AndroidArticlePageObject;
import ui.ios.IOSArticlePageObject;

public class ArticlePageObjectFactory {
    public static ArticlePageObject get(AppiumDriver driver)
    {
        if (Platform.getInstance().isAndroid())
        {
            return new AndroidArticlePageObject(driver);
        }
        else
        {
            return new IOSArticlePageObject(driver);
        }
    }
}
