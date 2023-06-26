package tests.iOS;

import lib.iOSTestCase;
import org.junit.Test;
import ui.WelcomePageObject;

public class GetStartedTest extends iOSTestCase {
    @Test
    public void testPassThroughWelcome()
    {
        WelcomePageObject WelcomePageObject = new WelcomePageObject(driver);
        WelcomePageObject.waitForLearnMoreBtn();
        WelcomePageObject.clickSkipBtn();
        WelcomePageObject.clickGetStartedBtn();
    }
}
