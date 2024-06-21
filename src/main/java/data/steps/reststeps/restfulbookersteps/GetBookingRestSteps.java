package data.steps.reststeps.restfulbookersteps;

import data.models.requests.restfulbooker.BookerRequestObject;
import data.specbuilder.RequestSpecs;
import data.steps.reststeps.CommonRestSteps;
import io.qameta.allure.Step;
import io.restassured.specification.RequestSpecification;

import static data.constants.BookerData.BOOKING_ENDPOINT;
import static io.restassured.RestAssured.given;

public class GetBookingRestSteps extends CommonRestSteps<GetBookingRestSteps> {
    RequestSpecification baseRequestSpecification;
    public GetBookingRestSteps(){
        baseRequestSpecification = RequestSpecs.getRequestSpecForBookerToken();
    }

    @Step("Get Booking By ID")
    public BookerRequestObject getBookingByID(int id){
       return given(baseRequestSpecification)
               .when()
               .get(BOOKING_ENDPOINT + id)
               .then()
               .extract()
               .body().as(BookerRequestObject.class);
    }
    @Step("Get Booking By ID")
    public GetBookingRestSteps getBookingByNotFound(int id){
        response = given(baseRequestSpecification)
                .when()
                .get(BOOKING_ENDPOINT + id);
    return this;
    }
}
