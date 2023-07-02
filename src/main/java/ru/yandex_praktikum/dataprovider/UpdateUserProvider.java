package ru.yandex_praktikum.dataprovider;

import org.apache.commons.lang3.RandomStringUtils;
import ru.yandex_praktikum.pojo.UpdateUserRequest;

import java.util.Locale;

import static ru.yandex_praktikum.constant.BaseConstant.MAIL_PREFIX;

public class UpdateUserProvider {

    public static UpdateUserRequest getRandomUpdateUserRequest() {
        return UpdateUserRequest.builder()
                .email(RandomStringUtils.randomAlphabetic(8).toLowerCase(Locale.ROOT) + MAIL_PREFIX)
                .password(RandomStringUtils.randomAlphabetic(8))
                .name(RandomStringUtils.randomAlphabetic(8))
                .build();
    }
}
