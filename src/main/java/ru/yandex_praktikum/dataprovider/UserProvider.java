package ru.yandex_praktikum.dataprovider;

import org.apache.commons.lang3.RandomStringUtils;
import ru.yandex_praktikum.pojo.CreateUserRequest;

public class UserProvider {

    public static final String MAIL_PREFIX = "@mail.ru";

    public static CreateUserRequest getRandomUserRequest() {
        return CreateUserRequest.builder()
                .email(RandomStringUtils.randomAlphabetic(8) + MAIL_PREFIX)
                .password(RandomStringUtils.randomAlphabetic(8))
                .name(RandomStringUtils.randomAlphabetic(8))
                .build();
    }

    public static CreateUserRequest getRandomUserWithoutEmailRequest() {
        return CreateUserRequest.builder()
                .password(RandomStringUtils.randomAlphabetic(8))
                .name(RandomStringUtils.randomAlphabetic(8))
                .build();
    }

    public static CreateUserRequest getRandomUserWithoutPasswordRequest() {
        return CreateUserRequest.builder()
                .email(RandomStringUtils.randomAlphabetic(8) + MAIL_PREFIX)
                .name(RandomStringUtils.randomAlphabetic(8))
                .build();
    }

    public static CreateUserRequest getRandomUserWithoutNameRequest() {
        return CreateUserRequest.builder()
                .email(RandomStringUtils.randomAlphabetic(8) + MAIL_PREFIX)
                .password(RandomStringUtils.randomAlphabetic(8))
                .build();
    }
}
