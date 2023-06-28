package ui.factories;

import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;
import ui.ArticlePageObject;
import ui.android.AndroidArticlePageObject;
import ui.ios.IOSArticlePageObject;
import ui.mobile_web.MWArticlePageObject;

public class ArticlePageObjectFactory {
    public static ArticlePageObject get(RemoteWebDriver driver)
    {
        if (Platform.getInstance().isAndroid())
        {
            return new AndroidArticlePageObject(driver);
        }
        else if (Platform.getInstance().isIOS())
        {
            return new IOSArticlePageObject(driver);
        }
        else
        {
            return new MWArticlePageObject(driver);
        }

    }
}
