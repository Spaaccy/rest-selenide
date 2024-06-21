package data.steps.reststeps.ergaststeps;

import data.models.shared.ergast.DriverShared;
import data.steps.reststeps.CommonRestSteps;
import io.qameta.allure.Step;
import io.restassured.specification.RequestSpecification;

import static data.constants.ErgastData.*;
import static data.specbuilder.RequestSpecs.getBaseRequestSpecForErgast;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ErgastDriverRestSteps extends CommonRestSteps<ErgastDriverRestSteps> {
    RequestSpecification requestSpecification;
    DriverShared javaDriver;
    public ErgastDriverRestSteps() {
        requestSpecification = getBaseRequestSpecForErgast();
    }
    @Step("get updated pet using id")
    public ErgastDriverRestSteps getDrivers() {
        response = given(requestSpecification)
                .when()
                .get(ERGAST_ENDPOINT);
        return this;
    }
    @Step
    public ErgastDriverRestSteps createDriver() {
        javaDriver = new DriverShared();
        javaDriver.setDriverId(DRIVER_ID);
        javaDriver.setPermanentNumber(PERM_NUMBER);
        javaDriver.setCode(CODE);
        javaDriver.setUrl(URL);
        javaDriver.setGivenName(NAME);
        javaDriver.setFamilyName(FAMILY_NAME);
        javaDriver.setDateOfBirth(DATE);
        javaDriver.setNationality(NATIONALITY);
        return this;
    }

    @Step("validate driver")
    public void validateDriver() {
        assertThat(javaDriver, equalTo(driverShared));
    }
}
