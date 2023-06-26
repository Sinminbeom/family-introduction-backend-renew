package com.minbeom.familyintroductionbackendrenew.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class UserDTO {
    private String name;
    private String password;
    private String email;

    @Override
    public String toString() {
        return "UserDTO{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
