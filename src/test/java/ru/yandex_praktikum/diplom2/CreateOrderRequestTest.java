package ru.yandex_praktikum.diplom2;

import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.Matchers;
import org.junit.Test;
import ru.yandex_praktikum.clients.IngredientClient;
import ru.yandex_praktikum.clients.OrderClient;
import ru.yandex_praktikum.dataprovider.OrderProvider;
import ru.yandex_praktikum.dataprovider.UserProvider;
import ru.yandex_praktikum.pojo.CreateOrderRequest;
import ru.yandex_praktikum.pojo.CreateUserRequest;

import java.util.List;

public class CreateOrderRequestTest extends BaseTest {
    private final IngredientClient ingredientClient = new IngredientClient();
    private final OrderClient orderClient = new OrderClient();


    @Test
    @DisplayName("Authorized user can create order")
    public void authorizedUserCanCreateOrder() {
        CreateUserRequest createUserRequest = UserProvider.getRandomUserRequest();
        accessToken = userClient.register(createUserRequest)
                .extract().jsonPath().get("accessToken");

        List<String> ingredientIds = ingredientClient.getIngredientIds();

        CreateOrderRequest createOrderRequestWithIngredient = OrderProvider.getCreateOrderRequestWithIngredient(ingredientIds);

        orderClient.createOrder(accessToken, createOrderRequestWithIngredient)
                .statusCode(200)
                .body("success", Matchers.equalTo(true));

    }

    //тест падает, т.к. находит баг - неавторизованный юзер может создать заказ
    @Test
    @DisplayName("Unauthorized user can not create order")
    public void unauthorizedUserCanNotCreateOrder() {

        List<String> ingredientIds = ingredientClient.getIngredientIds();

        CreateOrderRequest createOrderRequestWithIngredient = OrderProvider.getCreateOrderRequestWithIngredient(ingredientIds);

        orderClient.createOrder(createOrderRequestWithIngredient)
                .statusCode(401)
                .body("success", Matchers.equalTo(false))
                .and()
                .body("message", Matchers.equalTo("You should be authorised"));

    }

    @Test
    @DisplayName("Order without ingredients can not be created")
    public void orderWithoutIngredientsCanNotBeCreated() {
        CreateUserRequest createUserRequest = UserProvider.getRandomUserRequest();
        accessToken = userClient.register(createUserRequest)
                .extract().jsonPath().get("accessToken");

        CreateOrderRequest createOrderRequestWithoutIngredient = OrderProvider.getCreateOrderRequestWithoutIngredient();

        orderClient.createOrder(accessToken, createOrderRequestWithoutIngredient)
                .statusCode(400)
                .body("success", Matchers.equalTo(false))
                .and()
                .body("message", Matchers.equalTo("Ingredient ids must be provided"));

    }

    @Test
    @DisplayName("Order with incorrect ingredient hash can not be created")
    public void orderWithIncorrectIngredientHashCanNotBeCreated() {
        CreateUserRequest createUserRequest = UserProvider.getRandomUserRequest();
        accessToken = userClient.register(createUserRequest)
                .extract().jsonPath().get("accessToken");

        CreateOrderRequest createOrderRequestWithBadIngredientHash = OrderProvider.getCreateOrderRequestWithBadIngredientHash();

        orderClient.createOrder(accessToken, createOrderRequestWithBadIngredientHash)
                .statusCode(500);

    }
}
