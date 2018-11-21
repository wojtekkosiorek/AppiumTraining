package Framework;

import com.google.gson.Gson;
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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;

public class BaseDriver {
    private final String hubUrl= "http://127.0.0.1:4729/wd/hub";

    public void setDriverContext(ITestContext context) throws MalformedURLException, FileNotFoundException {
        context.setAttribute("driver", initDriver());
        context.setAttribute("wait", initWait((AndroidDriver<AndroidElement>) context.getAttribute("driver")));
    }

    private FluentWait<WebDriver> initWait(AndroidDriver<AndroidElement> driver) {
        return new WebDriverWait(driver, 30)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NullPointerException.class)
                .ignoring(ClassCastException.class)
                .ignoring(NoSuchElementException.class);
    }

    private AndroidDriver<AndroidElement> initDriver() throws MalformedURLException, FileNotFoundException {
        return new AndroidDriver<AndroidElement>(new URL(hubUrl), getCapabilities());
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
