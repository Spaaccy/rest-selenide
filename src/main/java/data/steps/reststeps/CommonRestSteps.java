package data.steps.reststeps;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import data.models.requests.restfulbooker.BookerMainRequest;
import data.models.requests.restfulbooker.BookerRequestObject;
import data.models.requests.restfulbooker.BookerUserRequest;
import data.models.responses.bookstore.Book;
import data.models.responses.petstore.ImageUploadResponse;
import data.models.responses.restfulbooker.BookerAuthResponse;
import data.models.shared.ergast.DriverShared;
import data.models.shared.petstore.PetShared;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static data.constants.CommonData.*;
import static data.specbuilder.ResponseSpecs.createResponseCheckerSpec;

public class CommonRestSteps<T extends CommonRestSteps> {
    protected static ObjectMapper mapper = new ObjectMapper();
    protected static Faker faker = new Faker();
    protected static PetShared petSharedClass;
    protected static PetShared petResponseClass;
    protected static Book bookStoreResponseClass;
    protected static ImageUploadResponse imageUploadResponseClass;
    protected static DriverShared driverShared;
    protected static BookerAuthResponse bookerAuthResponse;
    protected static BookerUserRequest bookerUserRequest;
    protected static BookerMainRequest bookingRequest;
    protected static BookerMainRequest bookingResponse;
    protected static BookerRequestObject bookerRequestObject;
    protected static BookerRequestObject bookerResponseObjectFromUpdate;
    protected Response response;
    protected ValidatableResponse validatableResponse;
    protected RequestSpecification requestSpecification;


    public T getValidatableResponse() {
        validatableResponse = response
                .then()
                .assertThat();
        return (T) this;
    }
    public T checkStatusCode() {
        validatableResponse
                .spec(createResponseCheckerSpec(SUCCESS_CODE));
        return (T) this;
    }
    public void checkStatusCodeDelete() {
        validatableResponse
                .spec(createResponseCheckerSpec(SUCCESS_CODE_CREATED));
    }
    public void checkStatusCodeNotFound() {
        validatableResponse
                .spec(createResponseCheckerSpec(ERROR_CODE));
    }
    public T extractBookStoreResponseAsClass() {
        bookStoreResponseClass = validatableResponse
                .extract()
                .as(Book.class);
        return (T) this;
    }
    public T extractPetStoreResponseAsClass() {
        petResponseClass = validatableResponse
                .extract()
                .as(PetShared.class);
        return (T) this;
    }
    public T extractPetStoreImageUploadResponseAsClass() {
        imageUploadResponseClass = validatableResponse
                .extract()
                .as(ImageUploadResponse.class);
        return (T) this;
    }
    public T extractDriverResponseAsClassUsingIndex(int index) {
        driverShared = validatableResponse
                .extract()
                .jsonPath()
                .getObject("MRData.DriverTable.Drivers["+index+"]", DriverShared.class);
        return (T) this;
    }
    public int getBookerResponseID() {
        return bookingResponse.getBookingid();
    }
}
