package ru.yandex_praktikum.clients;

import java.util.List;

import static io.restassured.RestAssured.given;
import static ru.yandex_praktikum.constant.BaseConstant.API_INGREDIENTS;

public class IngredientClient extends BaseClient {

    public List<String> getIngredientIds() {
        return given()
                .spec(getSpec())
                .when()
                .get(API_INGREDIENTS)
                .then()
                .extract().jsonPath().get("data._id");
    }
}
