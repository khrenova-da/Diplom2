package ru.yandex_praktikum.pojo;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class LoginUserRequest {
    private String email;
    private String password;
    public static LoginUserRequest from(CreateUserRequest createUserRequest) {
        return new LoginUserRequest(createUserRequest.getEmail(), createUserRequest.getPassword());
    }
    public static LoginUserRequest from(UpdateUserRequest updateUserRequest) {
        return new LoginUserRequest(updateUserRequest.getEmail(), updateUserRequest.getPassword());
    }
    public static LoginUserRequest getLoginUserRequestWithoutEmailFrom(CreateUserRequest createUserRequest) {
        return new LoginUserRequest(null, createUserRequest.getPassword());
    }
    public static LoginUserRequest getLoginUserRequestWithoutPasswordFrom(CreateUserRequest createUserRequest) {
        return new LoginUserRequest(createUserRequest.getEmail(), null);
    }
}
