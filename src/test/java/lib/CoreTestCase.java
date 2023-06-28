package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import ui.WelcomePageObject;
import lib.Platform;

import java.io.FileOutputStream;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class CoreTestCase
{

    protected RemoteWebDriver driver;

    @Before
    @Step("Run driver and session")
    public void setUp() throws Exception
    {
        driver = Platform.getInstance().getDriver();
        this.createAllurePropertyFile();
        this.rotateScreenPortrait();
        this.skipWelcomePageForIOSApp();
        this.openWikiWebPageForMobileWeb();
    }
    @After
    @Step("Remove driver and session")
    public void tearDown()
    {
        driver.quit();
    }
    @Step("rotateScreenPortrait")
    protected void rotateScreenPortrait()
    {
        if (driver instanceof AppiumDriver)
    {
        AppiumDriver driver = (AppiumDriver) this.driver;
        driver.rotate(ScreenOrientation.PORTRAIT);
    }
        else
        {
            System.out.println("Method rotate screen does nothing for" + Platform.getInstance().getPlatformVar());
        }

    }
    @Step("rotateScreenLandscape")
    protected void rotateScreenLandscape()
    {
        if (driver instanceof AppiumDriver)
        {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.LANDSCAPE);
        }
        else
        {
            System.out.println("Method rotateScreenLandscape does nothing for" + Platform.getInstance().getPlatformVar());
        }
    }
    @Step("backgroundApp (not working for MW)")
    protected void backgroundApp(int seconds)
    {
        if (driver instanceof AppiumDriver)
        {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.runAppInBackground(Duration.ofSeconds(seconds));
        }
        else
        {
            System.out.println("Method backgroundApp does nothing for" + Platform.getInstance().getPlatformVar());
        }

    }

    @Step("openWikiWebPageForMobileWeb only for MW")
    protected void openWikiWebPageForMobileWeb()
    {
        if(Platform.getInstance().isMW())
        {
            driver.get("https://en.m.wikipedia.org");
        }
        else
        {
            System.out.println("Method openWikiWebPageForMobileWeb does nothing for" + Platform.getInstance().getPlatformVar());
        }
    }
    @Step("skipWelcomePageForIOSApp")
    private void skipWelcomePageForIOSApp()
    {
        if(Platform.getInstance().isIOS())
        {
            AppiumDriver driver = (AppiumDriver) this.driver;
            WelcomePageObject WelcomePageObject = new WelcomePageObject(driver);
            WelcomePageObject.clickSkipBtn();
        }
    }
    private void createAllurePropertyFile()
    {
        String path = System.getProperty("Allure.results.directory");
        try
        {
            Properties props = new Properties();
            FileOutputStream fos = new FileOutputStream(path + "/environment.xml");
            props.setProperty("Environment", Platform.getInstance().getPlatformVar());
            props.store(fos, "See https://docs.qameta.io/allure/#_environment");
            fos.close();
        }
        catch (Exception e)
        {
            System.err.println("IO problem when writing allure properties file");
            e.printStackTrace();
        }
    }

}
