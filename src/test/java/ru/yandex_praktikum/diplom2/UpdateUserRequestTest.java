package ru.yandex_praktikum.diplom2;

import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.Matchers;
import org.junit.Test;
import ru.yandex_praktikum.dataprovider.UpdateUserProvider;
import ru.yandex_praktikum.dataprovider.UserProvider;
import ru.yandex_praktikum.pojo.CreateUserRequest;
import ru.yandex_praktikum.pojo.LoginUserRequest;
import ru.yandex_praktikum.pojo.UpdateUserRequest;

public class UpdateUserRequestTest extends BaseTest {

    @Test
    @DisplayName("Authenticated user can update user data")
    public void authenticatedUserCanUpdateUserData() {
        CreateUserRequest createUserRequest = UserProvider.getRandomUserRequest();
        //создание пользователя
        accessToken = userClient.register(createUserRequest)
                .extract().jsonPath().get("accessToken");


        UpdateUserRequest randomUpdateUserRequest = UpdateUserProvider.getRandomUpdateUserRequest();

        userClient.update(accessToken, randomUpdateUserRequest)
                .statusCode(200)
                .body("success", Matchers.equalTo(true))
                .and()
                .body("user.email", Matchers.equalTo(randomUpdateUserRequest.getEmail()))
                .and()
                .body("user.name", Matchers.equalTo(randomUpdateUserRequest.getName()));

        LoginUserRequest loginUserRequest = LoginUserRequest.from(randomUpdateUserRequest);

        userClient.login(loginUserRequest)
                .statusCode(200)
                .body("success", Matchers.equalTo(true));
    }

    @Test
    @DisplayName("Not authenticated user can not update user data")
    public void notAuthenticatedUserCanNotUpdateUserData() {

        UpdateUserRequest randomUpdateUserRequest = UpdateUserProvider.getRandomUpdateUserRequest();

        userClient.update(randomUpdateUserRequest)
                .statusCode(401)
                .body("success", Matchers.equalTo(false))
                .and()
                .body("message", Matchers.equalTo("You should be authorised"));

    }

}
