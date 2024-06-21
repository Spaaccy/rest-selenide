package data.specbuilder;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static data.constants.BookStoreData.BOOK_STORE_URI;
import static data.constants.BookerData.BOOKER_BASE_URI;
import static data.constants.ErgastData.ERGAST_URI;
import static data.constants.PetStoreData.PET_STORE_URL;


public class RequestSpecs {
    public static RequestSpecification getRequestSpecForBookerToken() {
        return new RequestSpecBuilder()
                .addFilter(new AllureRestAssured())
                .setBaseUri(BOOKER_BASE_URI)
                .setContentType(ContentType.JSON)
                .build();
    }
    public static RequestSpecification getRequestSpecForBooker(String token) {
        return new RequestSpecBuilder()
                .addFilter(new AllureRestAssured())
                .setBaseUri(BOOKER_BASE_URI)
                .setContentType(ContentType.JSON)
                .addHeader("Cookie", "token=" + token)
                .build();
    }

    public static RequestSpecification getBaseRequestSpecForBooking() {
        return new RequestSpecBuilder()
                .addFilter(new AllureRestAssured())
                .setBaseUri(BOOK_STORE_URI)
                .setContentType(ContentType.JSON)
                .build();
    }


    public static RequestSpecification getBaseRequestSpecForPetStore() {
        return new RequestSpecBuilder()
                .addFilter(new AllureRestAssured())
                .setBaseUri(PET_STORE_URL)
                .setContentType(ContentType.JSON)
                .build();
    }

    public static RequestSpecification getBaseRequestSpecForErgast() {
        return new RequestSpecBuilder()
                .addFilter(new AllureRestAssured())
                .setBaseUri(ERGAST_URI)
                .setContentType(ContentType.JSON)
                .build();
    }
}
