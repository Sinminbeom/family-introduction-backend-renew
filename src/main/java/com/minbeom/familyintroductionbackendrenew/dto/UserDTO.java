package com.minbeom.familyintroductionbackendrenew.dto;

import com.minbeom.familyintroductionbackendrenew.domain.User;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserDTO {
    private String name;
    private String password;
    private String email;

    public static User toUser(UserDTO userDTO) {
        return User.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .build();
    }
}
