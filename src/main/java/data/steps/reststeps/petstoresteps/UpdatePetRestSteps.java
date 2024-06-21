package data.steps.reststeps.petstoresteps;

import data.steps.reststeps.CommonRestSteps;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;

import static data.specbuilder.RequestSpecs.getBaseRequestSpecForPetStore;
import static io.restassured.RestAssured.given;

public class UpdatePetRestSteps extends CommonRestSteps<UpdatePetRestSteps> {
    public UpdatePetRestSteps() {
        requestSpecification = getBaseRequestSpecForPetStore();
    }
    @Step("change pet name in pojo")
    public UpdatePetRestSteps updatePetPOJOName(String petPOJOName) {
        petSharedClass.setName(petPOJOName);
        return this;
    }
    @Step("change pet status in pojo")
    public UpdatePetRestSteps updatePetPOJOStatus(String petPOJOStatus) {
        petSharedClass.setStatus(petPOJOStatus);
        return this;
    }
    @Step("send updated pet")
    public UpdatePetRestSteps updatePet() {
        response = given(requestSpecification)
                .contentType(ContentType.URLENC)
                .formParam("name", petSharedClass.getName())
                .formParam("status", petSharedClass.getStatus())
                .when()
                .post("/pet/{petId}", petSharedClass.getId());
        return this;
    }
}
