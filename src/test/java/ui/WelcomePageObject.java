package ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WelcomePageObject extends MainPageObject{
    private static final String
    LEARN_MORE_BTN="",
    SKIP_BTN_IOS="",
    GET_STARTED_BTN="";
    public WelcomePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
    public void waitForLearnMoreBtn()
    {
        this.waitForElementPresent(LEARN_MORE_BTN, "Cannot find Learn more", 2);
    }
    public void clickSkipBtn()
    {
        this.waitForElementandClick(SKIP_BTN_IOS, "Cannot click skip btn", 2);
    }
    public void clickGetStartedBtn()
    {
        this.waitForElementandClick(GET_STARTED_BTN, "Cannot click GetStarted btn", 2);
    }
}
