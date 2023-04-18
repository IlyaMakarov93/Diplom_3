package databaseuri;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class URL {
    protected static final String URL = "https://stellarburgers.nomoreparties.site/";
    protected static final String USER_ACTIONS = "api/auth/user/";
    protected static final String CREATE_USER = "api/auth/register/";

    protected RequestSpecification getBaseReqSpec() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(URL)
                .build();
    }
}

