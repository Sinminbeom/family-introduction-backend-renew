package com.minbeom.familyintroductionbackendrenew.dto;

import com.minbeom.familyintroductionbackendrenew.domain.Comment;
import com.minbeom.familyintroductionbackendrenew.domain.Data;
import com.minbeom.familyintroductionbackendrenew.domain.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CommentDTO {
    @NotBlank(message = "User는 필수 값입니다.")
    private User profile;
    @NotBlank(message = "comment는 필수 값입니다.")
    private Data data;

    public static Comment toComment(CommentDTO commentDTO) {
        Comment comment = Comment.builder()
                .data(commentDTO.getData())
                .profile(commentDTO.getProfile()).build();
        return comment;
    }

    @Override
    public String toString() {
        return "CommentDTO{" +
                "profile=" + profile +
                ", data=" + data +
                '}';
    }
}
