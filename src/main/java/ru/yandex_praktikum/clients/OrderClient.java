package ru.yandex_praktikum.clients;

import io.restassured.response.ValidatableResponse;
import ru.yandex_praktikum.pojo.CreateOrderRequest;

import static io.restassured.RestAssured.given;
import static ru.yandex_praktikum.constant.BaseConstant.CREATE_ORDER_URL;
import static ru.yandex_praktikum.constant.BaseConstant.GET_USER_ORDERS;

public class OrderClient extends BaseClient {


    public ValidatableResponse createOrder(String token, CreateOrderRequest createOrderRequest) {
        return given()
                .spec(getSpec())
                .header("Authorization", token)
                .body(createOrderRequest)
                .when()
                .post(CREATE_ORDER_URL)
                .then();
    }

    public ValidatableResponse createOrder(CreateOrderRequest createOrderRequest) {
        return given()
                .spec(getSpec())
                .body(createOrderRequest)
                .when()
                .post(CREATE_ORDER_URL)
                .then();
    }

    public ValidatableResponse getUserOrders(String token) {
        return given()
                .spec(getSpec())
                .header("Authorization", token)
                .when()
                .get(GET_USER_ORDERS)
                .then();
    }

    public ValidatableResponse getUserOrders() {
        return given()
                .spec(getSpec())
                .when()
                .get(GET_USER_ORDERS)
                .then();
    }
}
