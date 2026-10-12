package Controllers;

import dto.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.BaseApi;

import static io.restassured.RestAssured.given;

public class AuthController implements BaseApi {

    public Response registrationLogin(User user, String endPoint) {
        return given()
                .body(user)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL + endPoint)
                .thenReturn();
    }
}
