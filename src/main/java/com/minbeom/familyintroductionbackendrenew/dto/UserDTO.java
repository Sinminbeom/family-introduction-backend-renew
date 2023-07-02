package com.minbeom.familyintroductionbackendrenew.dto;

import com.minbeom.familyintroductionbackendrenew.domain.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter @Setter
public class UserDTO {
    @NotBlank(message = "name은 필수 값입니다.")
    private String name;
    @NotBlank(message = "password은 필수 값입니다.")
    private String password;
    @Email
    @NotBlank(message = "email은 필수 값입니다.")
    private String email;

    public static User toUser(UserDTO userDTO) {
        return User.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .build();
    }
}
