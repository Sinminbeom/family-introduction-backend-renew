package com.minbeom.familyintroductionbackendrenew.domain;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Board {
    private Long id;
    private String text;
    private Long createUserId;
    private Long updateUserId;

    public Board(String text, Long createUserId, Long updateUserId) {
        this.text = text;
        this.createUserId = createUserId;
        this.updateUserId = updateUserId;
    }
}
