package com.minbeom.familyintroductionbackendrenew.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private Long id;
    private User profile;
    private Data data;
    private Long parentId;
    private Long boardId;
    //    private String text;
    //    private List<Comment> replies;
    //    private Long userId;
    //    private String userName;

}
