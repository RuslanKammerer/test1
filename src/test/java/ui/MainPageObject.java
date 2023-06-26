package ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject {
    protected AppiumDriver driver;
    public MainPageObject(AppiumDriver driver)
    {
        this.driver = driver;
    }
    public WebElement waitForElementPresent(String locator, String err_msg, long time_sec)
    {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, time_sec);
        wait.withMessage(err_msg + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }
    public WebElement waitForElementPresent(String locator, String err_msg)

    {
        return waitForElementPresent(locator, err_msg, 3);
    }

    public WebElement waitForElementandClick(String locator, String err_msg, long time_sec)
    {
        WebElement element = waitForElementPresent(locator,err_msg,time_sec);
        element.click();
        return element;
    }
    public WebElement waitForElementandSendKeys(String locator, String value, String err_msg, long time_sec)
    {
        WebElement element = waitForElementPresent(locator,err_msg,time_sec);
        element.sendKeys(value);
        return element;
    }
    public boolean waitForElementNotPresent(String locator, String err_msg, long time_sec)
    {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, time_sec);
        wait.withMessage(err_msg + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }
    public WebElement waitForElementAndClear(String locator, String err_msg, long time_sec)
    {
        WebElement element = waitForElementPresent(locator,err_msg,time_sec);
        element.clear();
        return element;
    }
    public void swipeUp(int timeOfSwipe)
    {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width/2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);
        action.press(x, start_y).waitAction(timeOfSwipe).moveTo(x, end_y).release().perform();
    }
    public void swipeUpQuick()
    {
        swipeUp(250);

    }
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
    public boolean isElementLocatedOnTheScreen(String locator)
    {
        int element_location_by_y = this.waitForElementPresent(locator, "cannot find element by locator",
                        2).getLocation().getY();
        int screen_size_by_y = driver.manage().window().getSize().getHeight();
        return element_location_by_y < screen_size_by_y;
    }
    public void swipeElemntToLeft(String locator, String err_msg)
    {
        WebElement element = waitForElementPresent(locator, err_msg, 10);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y+lower_y)/2;

        TouchAction action = new TouchAction(driver);
        action.press(right_x, middle_y).waitAction(300).moveTo(left_x,middle_y).release().perform();

    }
    public int getAmountofElement(String locator)
    {
        By by = this.getLocatorByString(locator);
        List elements = driver.findElements(by);
        return elements.size();
    }
    public void assertElementNotPresent(String locator, String err_msg)
    {
        int amount_of_el = getAmountofElement(locator);
        if (amount_of_el>0) {
            String def_msg = "An element '" + locator + "'supposed not to be in present";
            throw new AssertionError(def_msg + " " + err_msg);
        }
    }
    public void assertElementPresent(String locator, String err_msg)
    {
        int amount_of_el = getAmountofElement(locator);
        if (amount_of_el==0)
        {
            String def_msg = "An element '" + locator + "'supposed to be in present";
            throw new AssertionError(def_msg + " " + err_msg);
        }

    }
    public String waitForElementAndGetAtribute(String locator, String attriibute, String err_msg, long timeInsec)
    {
        WebElement element = waitForElementPresent(locator, err_msg, timeInsec);
        return element.getAttribute(attriibute);
    }
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
        else {
            throw new IllegalArgumentException("Cannot get type of locator " + locator_with_type);
        }
    }
}
