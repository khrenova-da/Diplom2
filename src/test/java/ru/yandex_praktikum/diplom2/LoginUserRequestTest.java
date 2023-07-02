package ru.yandex_praktikum.diplom2;

import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Test;
import ru.yandex_praktikum.clients.UserClient;
import ru.yandex_praktikum.dataprovider.UserProvider;
import ru.yandex_praktikum.pojo.CreateUserRequest;
import ru.yandex_praktikum.pojo.LoginUserRequest;

public class LoginUserRequestTest {
    private final UserClient userClient = new UserClient();
    private String accessToken;
    @Test
    @DisplayName("User with correct request should be login")
    public void userWithCorrectRequestShouldBeLogin() {
        CreateUserRequest createUserRequest = UserProvider.getRandomUserRequest();
        //создание пользователя
        accessToken = userClient.register(createUserRequest)
                .extract().jsonPath().get("accessToken");
        //логин
        LoginUserRequest loginUserRequest = LoginUserRequest.from(createUserRequest);
        userClient.login(loginUserRequest)
                .statusCode(200)
                .body("success", Matchers.equalTo(true));
    }
    @Test
    @DisplayName("User without email should not be login")
    public void userWithoutEmailShouldNotBeLogin() {
        CreateUserRequest createUserRequest = UserProvider.getRandomUserRequest();
        //создание пользователя
        accessToken = userClient.register(createUserRequest)
                .extract().jsonPath().get("accessToken");
        //логин
        LoginUserRequest loginUserRequest = LoginUserRequest.getLoginUserRequestWithoutEmailFrom(createUserRequest);
        userClient.login(loginUserRequest)
                .statusCode(401)
                .body("message", Matchers.equalTo("email or password are incorrect"));
    }
    @Test
    @DisplayName("User without password should not be login")
    public void userWithoutPasswordShouldNotBeLogin() {
        CreateUserRequest createUserRequest = UserProvider.getRandomUserRequest();
        //создание пользователя
        accessToken = userClient.register(createUserRequest)
                .extract().jsonPath().get("accessToken");
        //логин
        LoginUserRequest loginUserRequest = LoginUserRequest.getLoginUserRequestWithoutPasswordFrom(createUserRequest);
        userClient.login(loginUserRequest)
                .statusCode(401)
                .body("message", Matchers.equalTo("email or password are incorrect"));
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
