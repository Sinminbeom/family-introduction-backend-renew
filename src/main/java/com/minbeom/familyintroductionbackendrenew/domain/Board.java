package com.minbeom.familyintroductionbackendrenew.domain;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Board {
    private Long id;
    private String title;
    private String text;
    private Long createUserId;
    private String createUserName;
    private Long updateUserId;
    private String userEmail;

    public Board(String title, String text, Long createUserId, Long updateUserId) {
        this.title = title;
        this.text = text;
        this.createUserId = createUserId;
        this.updateUserId = updateUserId;
    }

    @Override
    public String toString() {
        return "Board{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", createUserId=" + createUserId +
                ", updateUserId=" + updateUserId +
                '}';
    }
}
