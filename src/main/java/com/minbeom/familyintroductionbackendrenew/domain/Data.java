package com.minbeom.familyintroductionbackendrenew.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor



public class Data {
    private String comment;
    private List<Comment> replies;

    @Override
    public String toString() {
        return "Data{" +
                "comment='" + comment + '\'' +
                ", replies=" + replies +
                '}';
    }
}
