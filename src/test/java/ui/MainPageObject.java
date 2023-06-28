package ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.aspectj.util.FileUtil;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import lib.Platform;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import java.time.Duration;

public class MainPageObject {
    protected RemoteWebDriver driver;
    public MainPageObject(RemoteWebDriver driver)
    {
        this.driver = driver;
    }
    @Step("waitForElementPresent")
    public WebElement waitForElementPresent(String locator, String err_msg, long time_sec)
    {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, time_sec);
        wait.withMessage(err_msg + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }
    @Step("waitForElementPresent")
    public WebElement waitForElementPresent(String locator, String err_msg)

    {
        return waitForElementPresent(locator, err_msg, 3);
    }
    @Step("waitForElementandClick")
    public WebElement waitForElementandClick(String locator, String err_msg, long time_sec)
    {
        WebElement element = waitForElementPresent(locator,err_msg,time_sec);
        element.click();
        return element;
    }
    @Step("waitForElementandSendKeys")
    public WebElement waitForElementandSendKeys(String locator, String value, String err_msg, long time_sec)
    {
        WebElement element = waitForElementPresent(locator,err_msg,time_sec);
        element.sendKeys(value);
        return element;
    }
    @Step("waitForElementNotPresent")
    public boolean waitForElementNotPresent(String locator, String err_msg, long time_sec)
    {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, time_sec);
        wait.withMessage(err_msg + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }
    @Step("waitForElementAndClear")
    public WebElement waitForElementAndClear(String locator, String err_msg, long time_sec)
    {
        WebElement element = waitForElementPresent(locator,err_msg,time_sec);
        element.clear();
        return element;
    }
    @Step("swipeUp")
    public void swipeUp(int timeOfSwipe)
    {
        if (driver instanceof AppiumDriver)
        {
            TouchAction action = new TouchAction((AppiumDriver) driver);
            Dimension size = driver.manage().window().getSize();
            int x = size.width/2;
            int start_y = (int) (size.height * 0.8);
            int end_y = (int) (size.height * 0.2);
            //action.press(x, start_y).waitAction(timeOfSwipe).moveTo(x, end_y).release().perform();
            action.press(PointOption.point(x, start_y)).
                    waitAction(WaitOptions.
                            waitOptions(Duration.ofMillis(timeOfSwipe))).
                    moveTo(PointOption.point(x, end_y)).
                    release().
                    perform();

        }
        else
        {
            System.out.println("Method swipeUP does nothing for platform" + Platform.getInstance().getPlatformVar());
        }
    }
    @Step("swipeUpQuick")
    public void swipeUpQuick()
    {
        swipeUp(250);

    }
    @Step("scrollWebPageUp")
    public void scrollWebPageUp()
    {
        if (Platform.getInstance().isMW())
        {
            JavascriptExecutor JSExecutor = (JavascriptExecutor) driver;
            JSExecutor.executeScript("window.scrollBy(0,250");
        }
        else
        {
            System.out.println("Method scrollWebPageUp does nothing for platform" + Platform.getInstance().getPlatformVar());
        }
    }
    @Step("scrollWebPageTillElementNotVisible")
    public void scrollWebPageTillElementNotVisible(String locator, String err_msg, int max_swipes)
    {
       int already_swiped = 0;
       WebElement element = this.waitForElementPresent(locator, err_msg);
       while (!this.isElementLocatedOnTheScreen(locator))
       {
           scrollWebPageUp();
           ++already_swiped;
           if (already_swiped > max_swipes)
           {
               Assert.assertTrue(err_msg, element.isDisplayed());
           }
       }
    }
    @Step("swipeUpToFindElement")
    public void swipeUpToFindElement(String locator, String err_msg, int max_swipes)
    {
        By by = this.getLocatorByString(locator);
        int already_swipes = 0;
        while (driver.findElements(by).size()==0)
        {
            if (already_swipes > max_swipes) {
                waitForElementPresent(locator, "Не удалось найти элемент по свайпу.\n"+err_msg,0);
                return;
            }
            swipeUpQuick();
            ++already_swipes;
        }
    }
    @Step("swipeUpTillElementAppear")
    public void swipeUpTillElementAppear(String locator, String err_msg, int max_swipes)
    {
        int already_swipes = 0;
        while (!this.isElementLocatedOnTheScreen(locator))
        {
            if (already_swipes > max_swipes)
            {
                Assert.assertTrue(err_msg, this.isElementLocatedOnTheScreen(locator));
            }
            swipeUpQuick();
            ++already_swipes;
        }
    }
    @Step("isElementLocatedOnTheScreen")
    public boolean isElementLocatedOnTheScreen(String locator)
    {
        int element_location_by_y = this.waitForElementPresent(locator, "cannot find element by locator",
                        2).getLocation().getY();
        if (Platform.getInstance().isMW())
        {
            JavascriptExecutor JSExecutor = (JavascriptExecutor) driver;
            Object js_result = JSExecutor.executeScript("return window.pageYOffset");
            element_location_by_y -= Integer.parseInt(js_result.toString());
        }
        int screen_size_by_y = driver.manage().window().getSize().getHeight();
        return element_location_by_y < screen_size_by_y;
    }
    @Step("clickElementToTheRightUpperCorner")
    public void clickElementToTheRightUpperCorner(String locator, String err_msg)
    {
        if (driver instanceof AppiumDriver)
        {
            WebElement element = this.waitForElementPresent(locator, err_msg);
            int right_x = element.getLocation().getX();
            int upper_y = element.getLocation().getY();
            int lower_y = upper_y + element.getSize().getHeight();
            int middle_y = (upper_y+lower_y)/2;
            int width = element.getSize().getWidth();

            int point_to_click_x = (right_x + width) - 3;
            int point_to_click_y = middle_y;

            TouchAction action = new TouchAction((AppiumDriver) driver);
            //action.tap(point_to_click_x, point_to_click_y).perform();
            action.tap(PointOption.point(point_to_click_x, point_to_click_y)).perform();
        }
        else
        {
            System.out.println("Method clickElementToTheRightUpperCorner does nothing for platform" + Platform.getInstance().getPlatformVar());
        }

    }
    @Step("swipeElementToLeft")
    public void swipeElementToLeft(String locator, String err_msg)
    {
        if (driver instanceof AppiumDriver)
        {
            WebElement element = waitForElementPresent(locator, err_msg, 10);
            int left_x = element.getLocation().getX();
            int right_x = left_x + element.getSize().getWidth();
            int upper_y = element.getLocation().getY();
            int lower_y = upper_y + element.getSize().getHeight();
            int middle_y = (upper_y+lower_y)/2;

            TouchAction action = new TouchAction((AppiumDriver) driver);
            //action.press(right_x, middle_y);
           //action.waitAction(300);
            action.press(PointOption.point(right_x, middle_y));
            action.waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)));

            if (Platform.getInstance().isAndroid())
            {
                //action.moveTo(left_x,middle_y);
                action.moveTo(PointOption.point(left_x, middle_y));
            }
            else
            {
                int offset_x = (-1 * element.getSize().getWidth());
                //action.moveTo(offset_x, 0);
                action.moveTo(PointOption.point(offset_x, 0));
            }
            action.release();
            action.perform();
        }
        else
        {
            System.out.println("Method swipeElementToLeft does nothing for platform" + Platform.getInstance().getPlatformVar());
        }

    }
    @Step("getAmountofElement")
    public int getAmountofElement(String locator)
    {
        By by = this.getLocatorByString(locator);
        List elements = driver.findElements(by);
        return elements.size();
    }
    @Step("isElementPresent")
    public boolean isElementPresent(String locator)
    {return getAmountofElement(locator) > 0;}
    @Step("assertElementNotPresent")
    public void assertElementNotPresent(String locator, String err_msg)
    {
        int amount_of_el = getAmountofElement(locator);
        if (amount_of_el>0) {
            String def_msg = "An element '" + locator + "'supposed not to be in present";
            throw new AssertionError(def_msg + " " + err_msg);
        }
    }
    @Step("assertElementPresent")
    public void assertElementPresent(String locator, String err_msg)
    {
        int amount_of_el = getAmountofElement(locator);
        if (amount_of_el==0)
        {
            String def_msg = "An element '" + locator + "'supposed to be in present";
            throw new AssertionError(def_msg + " " + err_msg);
        }

    }
    @Step("waitForElementAndGetAtribute")
    public String waitForElementAndGetAtribute(String locator, String attriibute, String err_msg, long timeInsec)
    {
        WebElement element = waitForElementPresent(locator, err_msg, timeInsec);
        return element.getAttribute(attriibute);
    }
    @Step("tryClickElementWithFewAttempts")
    public void tryClickElementWithFewAttempts(String locator, String err_msg, int amount_of_attempts)
    {
        int current_attempts = 0;
        boolean need_more_attempts = true;

        while (need_more_attempts)
        {
            try
            {
                this.waitForElementandClick(locator, err_msg, 1);
                need_more_attempts = false;
            }
            catch (Exception e)
            {
                if (current_attempts > amount_of_attempts)
                {
                    this.waitForElementandClick(locator, err_msg, 1);
                }
            }
            ++current_attempts;
        }
    }
    @Step("getLocatorByString")
    private By getLocatorByString(String locator_with_type)
    {
        String[] exploded_locator = locator_with_type.split(Pattern.quote(":"),2);
        String by_type = exploded_locator[0];
        String locator = exploded_locator[1];

        if (by_type.equals("xpath"))
        {
            return By.xpath(locator);
        }
        else if (by_type.equals("id"))
        {
            return By.id(locator);
        }
        else if (by_type.equals("css"))
        {
            return By.cssSelector(locator);
        }
        else {
            throw new IllegalArgumentException("Cannot get type of locator " + locator_with_type);
        }
    }
/*    public String takeScreenshot(String name)
    {
        TakesScreenshot ts = (TakesScreenshot)this.driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir"+"/"+ name + "_screenshot.png");
        try
        {
            FileUtils.copyFile(source, new File(path));
            System.out.println("The screenshot was taken: " + path);
        }
        catch (Exception e)
        {System.out.println("Cannot take screenshot" + e.getMessage());}
        return path;
    }*/
/*    @Attachment
    public static byte[] screenshot(String path)
    {
        byte[] bytes = new byte[0];
        try
        {
            bytes = Files.readAllBytes(Paths.get(path));
        }
        catch (IOException e)
        {
            System.out.println("Cannot get bytes from screenshot " + e.getMessage());
        }
        return bytes;
    }*/
}
