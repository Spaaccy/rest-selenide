package data.steps.reststeps.petstoresteps;

import data.steps.reststeps.CommonRestSteps;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;

import java.io.File;

import static data.constants.PetStoreData.PET_IMAGE_FORMAT;
import static data.constants.PetStoreData.PET_IMAGE_METADATA;
import static data.specbuilder.RequestSpecs.getBaseRequestSpecForPetStore;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UploadImageRestSteps extends CommonRestSteps<UploadImageRestSteps> {
    private File imageFile;
    public UploadImageRestSteps() {
        requestSpecification = getBaseRequestSpecForPetStore();
    }
    @Step("get image file")
    public UploadImageRestSteps getImage() {
        imageFile = new File("src/main/resources/randomImage.png");
        return this;
    }
    @Step("upload image for pet")
    public UploadImageRestSteps uploadPetImage() {
        response = given(requestSpecification)
                .contentType(ContentType.MULTIPART)
                .multiPart("additionalMetadata", PET_IMAGE_METADATA)
                .multiPart("file", imageFile, PET_IMAGE_FORMAT)
                .when()
                .post("/pet/{petId}/uploadImage", petSharedClass.getId());
        return this;
    }
    @Step("validate image metadata")
    public UploadImageRestSteps validateImageMetadata() {
        assertThat(imageUploadResponseClass.getMessage(), containsString(PET_IMAGE_METADATA));
        return this;
    }
    @Step("validate image filename")
    public UploadImageRestSteps validateImageFilename() {
        assertThat(imageUploadResponseClass.getMessage(), containsString(imageFile.getName()));
        return this;
    }
    @Step("validate image filesize")
    public void validateImageFilesize() {
        assertThat(imageUploadResponseClass.getMessage().split(" "), hasItemInArray(imageFile.length()+""));
    }
}
