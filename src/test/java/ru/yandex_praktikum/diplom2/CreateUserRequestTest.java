package ru.yandex_praktikum.diplom2;

import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Test;
import ru.yandex_praktikum.clients.UserClient;
import ru.yandex_praktikum.dataprovider.UserProvider;
import ru.yandex_praktikum.pojo.CreateUserRequest;

public class CreateUserRequestTest {
    private final UserClient userClient = new UserClient();
    private String accessToken;

    @Test
    @DisplayName("User with correct request should be register")
    public void userWithCorrectRequestShouldBeRegister() {
        CreateUserRequest createUserRequest = UserProvider.getRandomUserRequest();
        //создание
        accessToken = userClient.register(createUserRequest)
                .statusCode(200)
                .body("success", Matchers.equalTo(true))
                .extract().jsonPath().get("accessToken");
    }
    @Test
    @DisplayName("User re-registration is not possible")
    public void userReRegistrationIsNotPossible() {
        CreateUserRequest createUserRequest = UserProvider.getRandomUserRequest();
        //создание пользователя
        accessToken = userClient.register(createUserRequest)
                .extract().jsonPath().get("accessToken");
        //попытка повторного создания пользователя
        userClient.register(createUserRequest)
                .statusCode(403)
                .body("message", Matchers.equalTo("User already exists"));
    }
    @Test
    @DisplayName("User without email should not register")
    public void userWithoutEmailShouldNotRegister() {
        CreateUserRequest createUserRequest = UserProvider.getRandomUserWithoutEmailRequest();
        //попытка создания пользователя без email
        userClient.register(createUserRequest)
                .statusCode(403)
                .body("message", Matchers.equalTo("Email, password and name are required fields"));
    }
    @Test
    @DisplayName("User without email should not register")
    public void userWithoutPasswordShouldNotRegister() {
        CreateUserRequest createUserRequest = UserProvider.getRandomUserWithoutPasswordRequest();
        //попытка создания пользователя без email
        userClient.register(createUserRequest)
                .statusCode(403)
                .body("message", Matchers.equalTo("Email, password and name are required fields"));
    }
    @Test
    @DisplayName("User without email should not register")
    public void userWithoutNameShouldNotRegister() {
        CreateUserRequest createUserRequest = UserProvider.getRandomUserWithoutNameRequest();
        //попытка создания пользователя без email
        userClient.register(createUserRequest)
                .statusCode(403)
                .body("message", Matchers.equalTo("Email, password and name are required fields"));
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            userClient.delete(accessToken)
                    .statusCode(202)
                    .body("success", Matchers.equalTo(true));
        }
    }
}
