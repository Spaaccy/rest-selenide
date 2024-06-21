package ge.testpetstore;

import com.fasterxml.jackson.core.JsonProcessingException;
import data.steps.reststeps.petstoresteps.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static data.constants.PetStoreData.UPDATED_PET_NAME;
import static data.constants.PetStoreData.UPDATED_PET_STATUS;

public class PetStoreAPI {
    AddPetRestSteps addPetSteps;
    FindPetRestSteps findPetSteps;
    UpdatePetRestSteps updatePetSteps;
    GetPetRestSteps getPetSteps;
    UploadImageRestSteps uploadImageSteps;

    @BeforeClass(alwaysRun = true)
    public void initiateSteps() {
        addPetSteps = new AddPetRestSteps();
        findPetSteps = new FindPetRestSteps();
        updatePetSteps = new UpdatePetRestSteps();
        getPetSteps = new GetPetRestSteps();
        uploadImageSteps = new UploadImageRestSteps();
    }
    @Test(priority = 1)
    public void addPetTest() {
        addPetSteps
                .createPetCategory()
                .createPetTag()
                .createPetBody()
                .addPet()
                .getValidatableResponse()
                .checkStatusCode()
                .extractPetStoreResponseAsClass()
                .validateAddedPetResponse();
    }
    @Test(priority = 2, dependsOnMethods = "addPetTest")
    public void findPetTest() throws JsonProcessingException {
        findPetSteps
                .getAddedPet()
                .getValidatableResponse()
                .checkStatusCode()
                .extractPetResponseAsClass()
                .validateResponseContainsID()
                .extractMyPet()
                .validatePetEqualsPOJO();
    }
    @Test(priority = 3, dependsOnMethods = {"addPetTest", "findPetTest"})
    public void updatePetTest() {
        updatePetSteps
                .updatePetPOJOName(UPDATED_PET_NAME)
                .updatePetPOJOStatus(UPDATED_PET_STATUS)
                .updatePet()
                .getValidatableResponse()
                .checkStatusCode();
    }
    @Test(priority = 4, dependsOnMethods = {"addPetTest", "findPetTest", "updatePetTest"})
    public void getPetTest() {
        getPetSteps
                .getPet()
                .getValidatableResponse()
                .checkStatusCode()
                .extractPetStoreResponseAsClass()
                .validatePetName()
                .validatePetStatus();
    }
    @Test(priority = 5, dependsOnMethods = "addPetTest")
    public void uploadImageTest() {
        uploadImageSteps
                .getImage()
                .uploadPetImage()
                .getValidatableResponse()
                .checkStatusCode()
                .extractPetStoreImageUploadResponseAsClass()
                .validateImageMetadata()
                .validateImageFilename()
                .validateImageFilesize();
    }
}
