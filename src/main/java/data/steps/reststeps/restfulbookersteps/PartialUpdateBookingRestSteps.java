package data.steps.reststeps.restfulbookersteps;

import data.models.requests.restfulbooker.BookerPartialUpdateRequest;
import data.models.requests.restfulbooker.BookerRequestObject;
import data.specbuilder.RequestSpecs;
import data.steps.reststeps.CommonRestSteps;

import static data.constants.BookerData.BOOKING_ENDPOINT;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PartialUpdateBookingRestSteps extends CommonRestSteps<PartialUpdateBookingRestSteps> {
    private BookerPartialUpdateRequest bookerPartialUpdateRequest;
    public PartialUpdateBookingRestSteps(){
        requestSpecification = RequestSpecs.getRequestSpecForBooker(bookerAuthResponse.getToken());
    }
//    public PartialUpdateBookingRestSteps partialUpdateBookerBody() {
//        bookerRequestObject.setFirstname(faker.funnyName().name());
//        bookerRequestObject.setLastname(faker.name().lastName());
//        bookingRequest.setBooking(bookerRequestObject);
//        return this;
//    }
    public PartialUpdateBookingRestSteps createBookerFullNameBody() {
        bookerPartialUpdateRequest = new BookerPartialUpdateRequest();
        bookerPartialUpdateRequest.setFirstName("luka");
        bookerPartialUpdateRequest.setLastName("magda");
        bookingRequest.getBooking().setFirstname("luka");
        bookingRequest.getBooking().setLastname("magda");
        return this;
    }

    public PartialUpdateBookingRestSteps partialUpdateBookingByID() {
        response = given(requestSpecification)
                        .body(bookerPartialUpdateRequest)
                        .when()
                        .patch(BOOKING_ENDPOINT + bookingResponse.getBookingid());
        return this;
    }
    public void extractAddedBooker() {
        bookerResponseObjectFromUpdate = validatableResponse
                .extract()
                .body()
                .as(BookerRequestObject.class);
    }
    public void validateUpdatedBookers(BookerRequestObject bookerResponseFromGet) {
        assertThat(bookerResponseObjectFromUpdate, equalTo(bookerResponseFromGet));
    }
}
