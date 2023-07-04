package ru.yandex_praktikum.pojo;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserRequest {
    private String email;
    private String password;
    private String name;


}
