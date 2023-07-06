package ru.yandex_praktikum.diplom2;

import org.hamcrest.Matchers;
import org.junit.After;
import ru.yandex_praktikum.clients.UserClient;

public class BaseTest {
    public final UserClient userClient = new UserClient();
    public String accessToken;

    @After
    public void tearDown() {
        if (accessToken != null) {
            userClient.delete(accessToken)
                    .statusCode(202)
                    .body("success", Matchers.equalTo(true));
        }
    }
}
