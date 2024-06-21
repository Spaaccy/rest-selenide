package ge.restfulbookertest;
import data.listeners.SelenideListener;
import data.models.requests.restfulbooker.BookerRequestObject;
import data.steps.reststeps.restfulbookersteps.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Test(groups = "RestTest")
public class RestfulBookerAPI {
    PartialUpdateBookingRestSteps partialUpdateBookingSteps;
    GetBookerTokenRestSteps getBookerTokenSteps;
    CreateBookingRestSteps createBookingSteps;
    GetBookingRestSteps getBookingSteps;
    DeleteBookingRestSteps deleteBookingSteps;
    @BeforeTest(alwaysRun = true)
    public void initializeToken() {
        getBookerTokenSteps = new GetBookerTokenRestSteps();
        getBookerTokenSteps
                .setUserInfo()
                .getRequestToken()
                .getValidatableResponse()
                .checkStatusCode()
                .extractTokenResponseAsClass();
    }

    @BeforeClass(alwaysRun = true)
    public void initiateSteps() {
        partialUpdateBookingSteps = new PartialUpdateBookingRestSteps();
        createBookingSteps = new CreateBookingRestSteps();
        getBookingSteps = new GetBookingRestSteps();
        deleteBookingSteps = new DeleteBookingRestSteps();
    }
    @Test(priority = 1)
    public void addBookingTest(){
        createBookingSteps
                .createBookerDates()
                .createBookerBody()
                .addBooker()
                .getValidatableResponse()
                .checkStatusCode()
                .extractAddedBooker();

        BookerRequestObject bookerResponseObject = getBookingSteps
                .getBookingByID(createBookingSteps.getBookerResponseID());
        createBookingSteps.validateUpdatedBookers(bookerResponseObject);
    }
    @Test(priority = 2, dependsOnMethods = "addBookingTest")
    public void updateBookingTest() {
        partialUpdateBookingSteps
                .createBookerFullNameBody()
                .partialUpdateBookingByID()
                .getValidatableResponse()
                .checkStatusCode()
                .extractAddedBooker();

        BookerRequestObject bookerResponseObject = getBookingSteps
                .getBookingByID(partialUpdateBookingSteps.getBookerResponseID());
        partialUpdateBookingSteps.validateUpdatedBookers(bookerResponseObject);
    }
    @Test(priority = 3, dependsOnMethods = {"addBookingTest", "updateBookingTest"})
    public void deleteBookingTest() {
        deleteBookingSteps
                .deleteBookingByID(deleteBookingSteps.getBookerResponseID())
                .getValidatableResponse()
                .checkStatusCodeDelete();

        getBookingSteps
                .getBookingByNotFound(partialUpdateBookingSteps.getBookerResponseID())
                .getValidatableResponse()
                .checkStatusCodeNotFound();
    }
}
