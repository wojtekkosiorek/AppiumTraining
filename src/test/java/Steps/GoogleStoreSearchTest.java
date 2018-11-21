package Steps;

import Framework.TestHooks;
import PageObjects.GoogleStorePage;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

@Epic("Basic google store test")
public class GoogleStoreSearchTest extends TestHooks {
    private GoogleStorePage googleStorePage;

    @BeforeMethod(groups = {"appium"})
    public void SetUp(ITestContext context) throws MalformedURLException, FileNotFoundException {
        googleStorePage = new GoogleStorePage(context);
    }

    @DataProvider(name = "appName")
    public Object[][] createAppNameData() {
        return new Object[][]{
                {"Messenger"},
                {"Linkedin"},
        };
    }

    @Test(groups = {"appium"}, dataProvider = "appName")
    @Story("Search application in the store")
    public void SearchAppInStore(String appName) {
        googleStorePage.googleStoreIsOpen();
        googleStorePage.searchApplication(appName);
        googleStorePage.backToPreviousView();
    }

}
