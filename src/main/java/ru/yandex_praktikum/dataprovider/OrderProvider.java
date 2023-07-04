package ru.yandex_praktikum.dataprovider;

import org.apache.commons.lang3.RandomStringUtils;
import ru.yandex_praktikum.pojo.CreateOrderRequest;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class OrderProvider {

    public static CreateOrderRequest getCreateOrderRequestWithIngredient(List<String> ingredients) {

        Random random = new Random();
        List<String> limitedIngredients = ingredients.stream()
                .limit(random.nextInt(ingredients.size()))
                .collect(Collectors.toList());
        return new CreateOrderRequest(limitedIngredients);
    }

    public static CreateOrderRequest getCreateOrderRequestWithoutIngredient() {
        return new CreateOrderRequest(Collections.emptyList());
    }

    public static CreateOrderRequest getCreateOrderRequestWithBadIngredientHash() {
        return new CreateOrderRequest(Collections.singletonList(RandomStringUtils.randomAlphabetic(8)));
    }


}
