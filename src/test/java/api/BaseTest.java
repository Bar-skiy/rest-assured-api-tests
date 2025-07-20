package api;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

    protected static RequestSpecification requestSpec;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://reqres.in/api";
        requestSpec = RestAssured.given();
        requestSpec = RestAssured
                .given()
                .header("x-api-key", "reqres-free-v1");

    }
}
