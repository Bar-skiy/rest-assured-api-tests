package api;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@Epic("Reqres API Tests")
@Feature("User API")
@DisplayName("Тесты для пользователей Reqres")
public class UserTests extends BaseTest {

    // Проверка, что пользователь с ID 2 существует и возвращается корректно
    @Test
    @Story("Получение информации о пользователе")
    @Description("Проверка, что пользователь с ID 2 существует и возвращается корректно")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("GET /users/2 — Проверка существующего пользователя")
   public void getUserById_shouldReturnUser() {
        given()
                .spec(requestSpec)
                .when()
                .get("/users/2")
                .then()
                .statusCode(200)
                .body("data.id", equalTo(2));
    }

    @Test
    @Story("Авторизация")
    @Description("Позитивный тест: успешная авторизация с корректными email и паролем")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("POST /login — Успешная авторизация")
    public void loginWithValidCredentials_shouldReturnToken() {
        given()
                .spec(requestSpec)
                .contentType(ContentType.JSON)
                .body("{\"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\"}")
                .when()
                .post("/login")
                .then()
                .statusCode(200)
                .body("token", notNullValue());
    }

    @Test
    @Story("Авторизация")
    @Description("Негативный тест: попытка авторизации без пароля должна вернуть ошибку 400")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("POST /login — Без пароля (ошибка 400)")
    public void loginWithoutPassword_shouldReturnError() {
        given()
                .spec(requestSpec)
                .contentType(ContentType.JSON)
                .body("{\"email\": \"eve.holt@reqres.in\"}") // без password
                .when()
                .post("/login")
                .then()
                .statusCode(400)
                .body("error", notNullValue());
    }

    @Test
    @Story("Создание пользователя")
    @Description("Позитивный тест: создание нового пользователя и проверка возвращаемых данных")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("POST /users — Создание пользователя")
    public void createUser_shouldReturnUserId() {
        given()
                .spec(requestSpec)
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
    @Story("Удаление пользователя")
    @Description("Удаление пользователя с ID 2. API должно вернуть 204 No Content")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("DELETE /users/2 — Удаление пользователя")
    public void deleteUser_shouldReturn204() {
        given()
                .spec(requestSpec)
                .when()
                .delete("/users/2")
                .then()
                .statusCode(204);
    }
}
