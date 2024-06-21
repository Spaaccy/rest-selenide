package data.steps.reststeps.restfulbookersteps;

import data.models.requests.restfulbooker.BookerDates;
import data.models.requests.restfulbooker.BookerMainRequest;
import data.models.requests.restfulbooker.BookerRequestObject;
import data.steps.reststeps.CommonRestSteps;
import io.restassured.specification.RequestSpecification;

import static data.constants.BookerData.BOOKING_ENDPOINT;
import static data.specbuilder.RequestSpecs.getRequestSpecForBooker;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CreateBookingRestSteps extends CommonRestSteps<CreateBookingRestSteps> {
    private BookerDates bookerDates;
    private RequestSpecification requestSpecification;
    public CreateBookingRestSteps() {
        requestSpecification = getRequestSpecForBooker(bookerAuthResponse.getToken());
    }
    public CreateBookingRestSteps createBookerDates() {
        bookerDates = new BookerDates();
        bookerDates.setCheckin("2018-01-01");
        bookerDates.setCheckout("2019-01-01");
        return this;
    }

    public CreateBookingRestSteps createBookerBody() {
        bookingRequest = new BookerMainRequest();
        bookerRequestObject = new BookerRequestObject();
        bookerRequestObject.setFirstname(faker.name().firstName());
        bookerRequestObject.setLastname(faker.name().lastName());
        bookerRequestObject.setTotalprice(faker.number().numberBetween(50, 100));
        bookerRequestObject.setDepositpaid(faker.bool().bool());
        bookerRequestObject.setBookingdates(bookerDates);
        bookerRequestObject.setAdditionalneeds(faker.cat().breed());
        bookingRequest.setBooking(bookerRequestObject);
        return this;
    }

    public CreateBookingRestSteps addBooker() {
        response =
                given(requestSpecification)
                        .body(bookingRequest.getBooking())
                        .post(BOOKING_ENDPOINT);
        return this;
    }
    public CreateBookingRestSteps extractAddedBooker() {
        bookingResponse = validatableResponse
                .extract()
                .body()
                .as(BookerMainRequest.class);
        return this;
    }

    public void validateUpdatedBookers(BookerRequestObject bookerResponseFromGet) {
        assertThat(bookingRequest.getBooking(), equalTo(bookerResponseFromGet));
    }
}
