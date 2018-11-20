package PageObjects;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.ITestContext;

public class GoogleStorePage {


    private AndroidDriver<AndroidElement> driver;
    private FluentWait<WebDriver> wait;

    private By idleSearchFieldLocatorId = By.id("search_box_idle_text");
    private By searchFieldLocatorId = By.id("search_box_text_input");


    public GoogleStorePage(ITestContext context) {
        this.driver = (AndroidDriver<AndroidElement>) context.getAttribute("driver");
        this.wait = (FluentWait<WebDriver>) context.getAttribute("wait");
    }

    public void googleStoreIsOpen() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(idleSearchFieldLocatorId));
    }

    public void searchApplication(String appName) {
        driver.findElement(idleSearchFieldLocatorId).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchFieldLocatorId));
        driver.findElement(searchFieldLocatorId).sendKeys(appName);
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
    }

    public void backToPreviousView() {
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));
        wait.until(ExpectedConditions.visibilityOfElementLocated(idleSearchFieldLocatorId));
    }


}
