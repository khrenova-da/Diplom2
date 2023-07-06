package ru.yandex_praktikum.constant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class BaseConstant {
    public static final String MAIL_PREFIX = "@mail.ru";
    public static final String API_INGREDIENTS = "/api/ingredients";
    public static final String REGISTER_URL = "/api/auth/register";
    public static final String CREATE_ORDER_URL = "/api/orders";
    public static final String USER_URL = "/api/auth/user";
    public static final String LOGIN_URL = "/api/auth/login";

    public static final String GET_USER_ORDERS = "/api/orders";


}
