package Steps;

import PageObjects.GoogleStorePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class GoogleStoreSearch {
    private GoogleStorePage googleStorePage;
    private AndroidDriver<AndroidElement> driver;
    private FluentWait<WebDriver> wait;

    @BeforeMethod
    public void SetUp(ITestContext context) throws MalformedURLException {
        driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4729/wd/hub"), getCapabilities());
        wait = new WebDriverWait(driver, 30)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NullPointerException.class)
                .ignoring(ClassCastException.class)
                .ignoring(NoSuchElementException.class);
        context.setAttribute("driver", this.driver);
        context.setAttribute("wait", this.wait);
        googleStorePage = new GoogleStorePage(context);


    }

    @DataProvider(name = "appName")
    public Object[][] createAppNameData() {
        return new Object[][]{
                {"Messenger"},
                {"Linkdin"},
        };
    }


    @Test(dataProvider = "appName", groups = {"regression"})
    public void testName(String appName) {
        googleStorePage.googleStoreIsOpen();
        googleStorePage.searchApplication(appName);
        googleStorePage.backToPreviousView();

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }


    private DesiredCapabilities getCapabilities() {
        DesiredCapabilities capabilities;
        capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.VERSION, "8.0.0");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Moto g6 play");
        capabilities.setCapability(MobileCapabilityType.UDID, "ZL42244F88");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability("appPackage", "com.android.vending");
        capabilities.setCapability("appActivity", "com.android.vending.AssetBrowserActivity");
        capabilities.setCapability("autoGrantPermissions", true);
        capabilities.setCapability("automationName", "UiAutomator2");

        return capabilities;
    }
}
