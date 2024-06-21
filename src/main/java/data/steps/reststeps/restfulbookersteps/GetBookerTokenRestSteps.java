package data.steps.reststeps.restfulbookersteps;

import data.models.requests.restfulbooker.BookerUserRequest;
import data.models.responses.restfulbooker.BookerAuthResponse;
import data.specbuilder.RequestSpecs;
import data.steps.reststeps.CommonRestSteps;
import io.restassured.specification.RequestSpecification;

import static data.constants.BookerData.AUTH_ENDPOINT;
import static io.restassured.RestAssured.given;

public class GetBookerTokenRestSteps extends CommonRestSteps<GetBookerTokenRestSteps> {
    RequestSpecification requestSpecification;
    public GetBookerTokenRestSteps setUserInfo(){
        bookerUserRequest = new BookerUserRequest();
        bookerUserRequest.setUserName("admin");
        bookerUserRequest.setPassword("password123");
        return this;
    }
    public GetBookerTokenRestSteps() {
        requestSpecification = RequestSpecs.getRequestSpecForBookerToken();
    }
    public GetBookerTokenRestSteps getRequestToken(){
        response = given(requestSpecification)
                .given()
                .body(bookerUserRequest)
                .when()
                .post(AUTH_ENDPOINT);
        return this;
    }
    public void extractTokenResponseAsClass() {
        bookerAuthResponse= validatableResponse
                .extract()
                .body()
                .as(BookerAuthResponse.class);
    }
}
