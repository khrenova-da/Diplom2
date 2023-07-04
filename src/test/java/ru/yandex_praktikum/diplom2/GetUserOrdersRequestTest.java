package ru.yandex_praktikum.diplom2;

import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.Matchers;
import org.junit.Test;
import ru.yandex_praktikum.clients.OrderClient;
import ru.yandex_praktikum.dataprovider.UserProvider;
import ru.yandex_praktikum.pojo.CreateUserRequest;

public class GetUserOrdersRequestTest extends BaseTest {
    private final OrderClient orderClient = new OrderClient();

    @Test
    @DisplayName("Authorized user can get list of user orders")
    public void authorizedUserCanGetListOfUserOrders() {
        CreateUserRequest createUserRequest = UserProvider.getRandomUserRequest();
        //создание пользователя
        accessToken = userClient.register(createUserRequest)
                .extract().jsonPath().get("accessToken");


        orderClient.getUserOrders(accessToken)
                .statusCode(200)
                .body("success", Matchers.equalTo(true));

    }

    @Test
    @DisplayName("Unauthorized user can not get list of user orders")
    public void unauthorizedUserCanNotGetListOfUserOrders() {

        orderClient.getUserOrders()
                .statusCode(401)
                .body("success", Matchers.equalTo(false))
                .and()
                .body("message", Matchers.equalTo("You should be authorised"));

    }

}
