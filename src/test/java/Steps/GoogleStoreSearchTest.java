package Steps;

import Framework.CapabilitiesSetup;
import PageObjects.GoogleStorePage;
import com.google.gson.Gson;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;

@Epic("Basic google store test")
public class GoogleStoreSearchTest {
    private GoogleStorePage googleStorePage;
    private AndroidDriver<AndroidElement> driver;
    private FluentWait<WebDriver> wait;

    @BeforeMethod(groups = {"regression"})
    public void SetUp(ITestContext context) throws MalformedURLException, FileNotFoundException {
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
                {"Linkedin"},
        };
    }

    @Test(groups = {"regression"}, dataProvider = "appName")
    @Story("Search application in the store")
    public void SearchAppInStore(String appName) {
        googleStorePage.googleStoreIsOpen();
        googleStorePage.searchApplication(appName);
        googleStorePage.backToPreviousView();

    }

    @AfterMethod(groups = {"regression"})
    public void tearDown() {
        driver.quit();
    }

    private DesiredCapabilities getCapabilities() throws FileNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader("./src/test/resources/capabilitiesSetup.json"));
        DesiredCapabilities capabilities = new DesiredCapabilities();
        Gson g = new Gson();
        CapabilitiesSetup capabilitiesSetup = g.fromJson(br, CapabilitiesSetup.class);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, capabilitiesSetup.getPlatformName());
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, capabilitiesSetup.getDeviceName());
        capabilities.setCapability(MobileCapabilityType.UDID, capabilitiesSetup.getUdid());
        capabilities.setCapability(MobileCapabilityType.VERSION, capabilitiesSetup.getVersion());
        capabilities.setCapability("appPackage", capabilitiesSetup.getAppPackage());
        capabilities.setCapability("appActivity", capabilitiesSetup.getAppActivity());
        capabilities.setCapability("autoGrantPermissions", capabilitiesSetup.getAutoGrantPermissions());
        capabilities.setCapability("automationName", capabilitiesSetup.getAutomationName());
        return capabilities;
    }
}
