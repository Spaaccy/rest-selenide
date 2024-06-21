package ge.testbookstore;

import data.steps.reststeps.bookstoresteps.GetBooksRestSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static data.constants.BookStoreData.*;

public class BookStoreAPI {
    GetBooksRestSteps getBooksSteps;
    @BeforeClass(alwaysRun = true)
    public void initiateSteps() {
        getBooksSteps = new GetBooksRestSteps();
    }
    @Test(priority = 1)
    public void validateBookingTest() {
        getBooksSteps
                .getBooksResponse()
                .getValidatableResponse()
                .checkStatusCode()
                .extractBookStoreResponseAsClass()
                .validateAllBookPagesAreLessThan(PAGES)
                .validateLastTwoBookAuthors(BEFORE_LAST, LAST_AUTHOR);
    }
}
