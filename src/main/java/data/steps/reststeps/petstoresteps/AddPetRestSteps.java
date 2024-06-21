package data.steps.reststeps.petstoresteps;
import data.models.shared.petstore.CategoryShared;
import data.models.shared.petstore.PetShared;
import data.models.shared.petstore.TagShared;
import data.steps.reststeps.CommonRestSteps;
import io.qameta.allure.Step;

import static data.constants.PetStoreData.PET_DEFAULT_STATUS;
import static data.constants.PetStoreData.PET_ENDPOINT;
import static data.specbuilder.RequestSpecs.getBaseRequestSpecForPetStore;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AddPetRestSteps extends CommonRestSteps<AddPetRestSteps> {
    private CategoryShared categoryShared;
    private TagShared tagShared;

    public AddPetRestSteps() {
        requestSpecification = getBaseRequestSpecForPetStore();
    }

    @Step("create pet category json")
    public AddPetRestSteps createPetCategory() {
        categoryShared = new CategoryShared();
        categoryShared.setId(faker.number().randomDigitNotZero());
        categoryShared.setName(faker.animal().name());
        return this;
    }

    @Step("create pet tag json")
    public AddPetRestSteps createPetTag() {
        tagShared = new TagShared();
        tagShared.setId(faker.number().randomDigitNotZero());
        tagShared.setName(faker.funnyName().name());
        return this;
    }

    @Step("create pet body json")
    public AddPetRestSteps createPetBody() {
        petSharedClass = new PetShared();
        petSharedClass.setId(faker.number().randomDigitNotZero());
        petSharedClass.setCategory(categoryShared);
        petSharedClass.setName(faker.cat().name());
        petSharedClass.setPhotoUrls(new String[] { faker.internet().url(), faker.internet().url() });
        petSharedClass.setTags(new TagShared[] {tagShared});
        petSharedClass.setStatus(PET_DEFAULT_STATUS);
        return this;
    }

    @Step("add pet and get the response")
    public AddPetRestSteps addPet(){
        response = given(requestSpecification)
                .body(petSharedClass)
                .when()
                .put(PET_ENDPOINT);
        return this;
    }
    public void validateAddedPetResponse() {
        assertThat(petResponseClass.getId(), equalTo(petSharedClass.getId()));
        assertThat(petResponseClass.getName(), equalTo(petSharedClass.getName()));
        assertThat(petResponseClass.getPhotoUrls(), arrayContainingInAnyOrder(petSharedClass.getPhotoUrls()));
        assertThat(petResponseClass.getTags()[0].getId(), equalTo(petSharedClass.getTags()[0].getId()));
        assertThat(petResponseClass.getTags()[0].getName(), equalTo(petSharedClass.getTags()[0].getName()));
        assertThat(petResponseClass.getStatus(), equalTo(petSharedClass.getStatus()));
    }
}
