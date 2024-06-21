package data.steps.reststeps.petstoresteps;

import data.steps.reststeps.CommonRestSteps;
import io.qameta.allure.Step;

import static data.specbuilder.RequestSpecs.getBaseRequestSpecForPetStore;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GetPetRestSteps extends CommonRestSteps<GetPetRestSteps> {
    public GetPetRestSteps() {
        requestSpecification = getBaseRequestSpecForPetStore();
    }
    @Step("get updated pet using id")
    public GetPetRestSteps getPet() {
        response = given(requestSpecification)
                .when()
                .get("/pet/{petId}", petSharedClass.getId());
        return this;
    }
    @Step("validate updated pet name")
    public GetPetRestSteps validatePetName() {
        assertThat(petSharedClass.getName(), equalTo(petSharedClass.getName()));
        return this;
    }
    @Step("validate updated pet status")
    public void validatePetStatus() {
        assertThat(petSharedClass.getStatus(), equalTo(petSharedClass.getStatus()));
    }
}
