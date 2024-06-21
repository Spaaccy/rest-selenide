package data.steps.reststeps.restfulbookersteps;

import data.steps.reststeps.CommonRestSteps;
import io.restassured.specification.RequestSpecification;

import static data.constants.BookerData.BOOKING_ENDPOINT;
import static data.specbuilder.RequestSpecs.getRequestSpecForBooker;
import static io.restassured.RestAssured.given;

public class DeleteBookingRestSteps extends CommonRestSteps<DeleteBookingRestSteps> {
    RequestSpecification requestSpecification;
    public DeleteBookingRestSteps() {
        requestSpecification = getRequestSpecForBooker(bookerAuthResponse.getToken());
    }
    public DeleteBookingRestSteps deleteBookingByID(int id) {
        response =
                given(requestSpecification)
                        .when()
                        .delete(BOOKING_ENDPOINT + id);
        return this;
    }
}
