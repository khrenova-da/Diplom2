package ru.yandex_praktikum.clients;

import io.restassured.response.ValidatableResponse;
import ru.yandex_praktikum.pojo.CreateUserRequest;
import ru.yandex_praktikum.pojo.LoginUserRequest;
import ru.yandex_praktikum.pojo.UpdateUserRequest;

import static io.restassured.RestAssured.given;
import static ru.yandex_praktikum.constant.BaseConstant.*;

public class UserClient extends BaseClient {


    public ValidatableResponse register(CreateUserRequest createUserRequest) {
        return given()
                .spec(getSpec())
                .body(createUserRequest)
                .when()
                .post(REGISTER_URL)
                .then();
    }

    public ValidatableResponse delete(String token) {
        return given()
                .spec(getSpec())
                .header("Authorization", token)
                .when()
                .delete(USER_URL)
                .then();
    }

    public ValidatableResponse login(LoginUserRequest loginUserRequest) {
        return given()
                .spec(getSpec())
                .body(loginUserRequest)
                .when()
                .post(LOGIN_URL)
                .then();
    }

    public ValidatableResponse update(String token, UpdateUserRequest updateUserRequest) {
        return given()
                .spec(getSpec())
                .header("Authorization", token)
                .body(updateUserRequest)
                .when()
                .patch(USER_URL)
                .then();
    }

    public ValidatableResponse update(UpdateUserRequest updateUserRequest) {
        return given()
                .spec(getSpec())
                .body(updateUserRequest)
                .when()
                .patch(USER_URL)
                .then();
    }
}
