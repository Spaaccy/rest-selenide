package ge.ergast;

import data.steps.reststeps.ergaststeps.ErgastDriverRestSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(groups = "RestTest")
public class ErgastAPI {
    ErgastDriverRestSteps ergastDriverSteps;
    @BeforeClass(alwaysRun = true)
    public void initiateSteps() {
        ergastDriverSteps = new ErgastDriverRestSteps();
    }
    @Test(priority = 1)
    public void ergastSteps() {
        ergastDriverSteps
                .createDriver()
                .getDrivers()
                .getValidatableResponse()
                .checkStatusCode()
                .extractDriverResponseAsClassUsingIndex(0)
                .validateDriver();
    }
}
