package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class CoreTestCase extends TestCase
{
    private static final String PLATFORM_IOS = "ios", PLATFORM_ANDROID = "android";
    protected AppiumDriver driver;
    private static String appiumURL = "http://127.0.0.1:4723/wd/hub";
    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        DesiredCapabilities capabilities = this.getCapabilitiesByPlatformEnv();

        driver = new AndroidDriver(new URL(appiumURL), capabilities);
        this.rotateScreenPortrait();
    }
    @Override
    protected void tearDown() throws Exception
    {
        driver.quit();
        super.tearDown();
    }
    protected void rotateScreenPortrait()
    {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }
    protected void rotateScreenLandscape()
    {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }
    protected void backgroundApp(int seconds)
    {
        driver.runAppInBackground(seconds);
    }
    private DesiredCapabilities getCapabilitiesByPlatformEnv() throws Exception
    {
        String platform = System.getenv("PLATFORM");
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (platform.equals(PLATFORM_ANDROID))
        {
            capabilities.setCapability("platformName","Android");
            capabilities.setCapability("deviceName","and8");
            capabilities.setCapability("platformVersion","8.0");
            capabilities.setCapability("automationName","Appium");
            capabilities.setCapability("appPackage","org.wikipedia");
            capabilities.setCapability("appActivity",".main.MainActivity");
        }
        else if (platform.equals(PLATFORM_IOS))
        {
        capabilities.setCapability("platformName","iOS");
        capabilities.setCapability("deviceName","iPhone SE");
        capabilities.setCapability("platformVersion","11.3");
        }
        else
        {
            throw new Exception("Platform error from env variable. Platform = "+platform);
        }
        return capabilities;
    }
}
