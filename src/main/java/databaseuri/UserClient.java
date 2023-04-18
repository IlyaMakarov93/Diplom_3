package databaseuri;

import informationuser.UserInformation;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class UserClient extends URL {
    @Step("Удаление пользователя")
    public boolean checkRemoveUser(String token) {
        return given()
                .spec(getBaseReqSpec())
                .header("authorization", token)
                .when()
                .delete(USER_ACTIONS)
                .then()
                .assertThat()
                .extract()
                .body()
                .path("message").equals("User successfully removed");
    }
    @Step("Cоздание пользователя")
    public ValidatableResponse createUser(UserInformation userInformation) {
        return given()
                .spec(getBaseReqSpec())
                .body(userInformation)
                .when()
                .post(CREATE_USER)
                .then()
                .assertThat()
                .statusCode(200)
                .body("success", is(true));
    }
}
