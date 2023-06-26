package tests;

import lib.CoreTestCase;
import lib.Platform;
import org.junit.Test;
import ui.WelcomePageObject;

public class GetStartedTest extends CoreTestCase {
    @Test
    public void testPassThroughWelcome()
    {
        if (Platform.getInstance().isAndroid()){
            return;
        }
        WelcomePageObject WelcomePageObject = new WelcomePageObject(driver);
        WelcomePageObject.waitForLearnMoreBtn();
        WelcomePageObject.clickSkipBtn();
        WelcomePageObject.clickGetStartedBtn();
    }
}
