package com.minbeom.familyintroductionbackendrenew.domain;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Calendar {
    private Long id;
    private String title;
    private String description;
    private Timestamp start;
    private Timestamp end;
    private Boolean allDay;
    private String color;
    private String textColor;
    private Long createUserId;
    private Long updateUserId;
}
