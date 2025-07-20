package api;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class UserTests extends BaseTest {

    // Проверка, что пользователь с ID 2 существует и возвращается корректно
    @Test
    void getUserById() {
        given()
                .when()
                .get("/users/2")
                .then()
                .statusCode(200)
                .body("data.id", equalTo(2));
    }

    // Позитивный тест: успешная авторизация с корректными email и паролем
    @Test
    void loginWithValidCredentials() {
        given()
                .header("x-api-key", "reqres-free-v1")
                .contentType(ContentType.JSON)
                .body("{\"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\"}")
                .when()
                .post("/login")
                .then()
                .statusCode(200)
                .body("token", notNullValue());
    }

    // Негативный тест: попытка авторизации без пароля должна вернуть ошибку 400
    @Test
    void loginWithoutPassword_shouldReturn400() {
        given()
                .header("x-api-key", "reqres-free-v1")
                .contentType(ContentType.JSON)
                .body("{\"email\": \"eve.holt@reqres.in\"}") // без password
                .when()
                .post("/login")
                .then()
                .statusCode(400)
                .body("error", notNullValue());
    }

    // Позитивный тест: создание нового пользователя и проверка возвращаемых данных
    @Test
    void createUser() {
        given()
                .header("x-api-key", "reqres-free-v1")
                .contentType(ContentType.JSON)
                .body("{\"name\": \"Morpheus\", \"job\": \"leader\"}")
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("name", equalTo("Morpheus"))
                .body("job", equalTo("leader"));
    }

    // Удаление пользователя с ID 2. API должно вернуть 204 No Content
    @Test
    void deleteUser() {
        given()
                .header("x-api-key", "reqres-free-v1")
                .when()
                .delete("/users/2")
                .then()
                .statusCode(204);
    }
}
