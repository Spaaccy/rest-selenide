package data.steps.reststeps.petstoresteps;

import data.models.shared.petstore.PetShared;
import data.steps.reststeps.CommonRestSteps;
import io.qameta.allure.Step;

import java.util.Arrays;

import static data.specbuilder.RequestSpecs.getBaseRequestSpecForPetStore;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class FindPetRestSteps extends CommonRestSteps<FindPetRestSteps> {
    private PetShared[] allPets;
    private PetShared myPet;
    public FindPetRestSteps() {
        requestSpecification = getBaseRequestSpecForPetStore();
    }

    @Step("get my added pet response")
    public FindPetRestSteps getAddedPet() {
        response = given(requestSpecification)
                .queryParam("status", "available")
                .when()
                .get("/pet/findByStatus");
        return this;
    }
    @Step("find and extract my added pet as object using pojo")
    public FindPetRestSteps extractPetResponseAsClass() {
        allPets = response
                .then()
                .extract()
                .body()
                .as(PetShared[].class);
        return this;
    }
    @Step("validate response contains my id")
    public FindPetRestSteps validateResponseContainsID() {
        assertThat(Arrays.asList(allPets), hasItem(hasProperty("id", equalTo(petSharedClass.getId()))));
        return this;
    }
    @Step("extract my pet")
    public FindPetRestSteps extractMyPet() {
        myPet = Arrays.stream(allPets).filter(pet -> pet.getId() == petSharedClass.getId()).findFirst().get();
        return this;
    }
    @Step("validate pet equals")
    public FindPetRestSteps validatePetEqualsPOJO() {
        assertThat(myPet, equalTo(petSharedClass));
        return this;
    }
}
