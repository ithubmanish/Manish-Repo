package StepDefinitions;

import Modules.DemoModules;
import Utilities.KeywordUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DemoSteps {

    @When("Click on Home Button")
    public static void clickHome(){
        DemoModules.clickHome();
    }


    @Then("Validate User is on Home Page of Application")
    public static void validateHome(){
    DemoModules.validateHome();
    }

    @When("Launch the desired application")
    public void launchTheDesiredApplication() {
        KeywordUtils.startServer();
    }
}
