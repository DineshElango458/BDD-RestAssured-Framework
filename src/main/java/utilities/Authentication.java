package utilities;

import io.restassured.RestAssured;
import io.restassured.authentication.FormAuthConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Authentication {

   

    public static Response getResponseUsingBasicAuth(String url, String username, String pass) {
        return RestAssured.given().auth().basic(username, pass).get(url);
    }

}
