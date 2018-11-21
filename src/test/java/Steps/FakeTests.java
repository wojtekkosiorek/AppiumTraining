package Steps;

import Framework.TestHooks;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

@Epic("All epic test")
@Feature("Feature")
public class FakeTests extends TestHooks {

    @Test (groups = {"smoke","regression"})
    @Story("Fake smoke test")
    @Description("Empty test description")
    public void SmokeTest() {
    }


    @Test (groups = {"regression"})
    @Story("Fake rege test")
    public void RegressionTest() {
    }
}
