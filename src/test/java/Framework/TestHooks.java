package Framework;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.testng.ITestContext;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeGroups;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

public class TestHooks {


    @BeforeGroups(groups = {"appium"})
    public void androidSetUp(ITestContext context) throws MalformedURLException, FileNotFoundException {
        BaseDriver baseDriver = new BaseDriver();
        baseDriver.setDriverContext(context);
    }


    @AfterGroups(groups = {"appium"})
    public void tearDown(ITestContext context) {
        ((AndroidDriver<AndroidElement>) context.getAttribute("driver")).quit();
    }

}
