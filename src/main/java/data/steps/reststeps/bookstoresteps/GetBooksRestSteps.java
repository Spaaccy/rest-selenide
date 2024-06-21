package data.steps.reststeps.bookstoresteps;
import data.steps.reststeps.CommonRestSteps;
import io.qameta.allure.Step;

import static data.constants.BookStoreData.BOOKS_ENDPOINT;
import static data.specbuilder.RequestSpecs.getBaseRequestSpecForBooking;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GetBooksRestSteps extends CommonRestSteps<GetBooksRestSteps> {
    public GetBooksRestSteps() {
        requestSpecification = getBaseRequestSpecForBooking();
    }
    @Step("get book response")
    public GetBooksRestSteps getBooksResponse() {
        response =
                given(requestSpecification)
                        .when()
                        .get(BOOKS_ENDPOINT);
        return this;
    }
    @Step("validate are book pages are less than: {0}")
    public GetBooksRestSteps validateAllBookPagesAreLessThan(int pages) {
        assertThat(bookStoreResponseClass.getBooks(), everyItem(hasProperty("pages", lessThan(pages))));
        return this;
    }
    @Step("validate first and second book authors equal: {0}")
    public void validateLastTwoBookAuthors(String authorBeforeLast, String authorLast) {
        assertThat(
                bookStoreResponseClass.getBooks()
                                .subList(bookStoreResponseClass.getBookSize()-2, bookStoreResponseClass.getBookSize())
                ,contains(
                    hasProperty("author", equalTo(authorBeforeLast)),
                    hasProperty("author", equalTo(authorLast))
        ));
    }
}
