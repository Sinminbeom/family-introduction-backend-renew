package com.minbeom.familyintroductionbackendrenew.dto;

import com.minbeom.familyintroductionbackendrenew.domain.Board;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class BoardDTO {
    @NotBlank(message = "text은 필수 값입니다.")
    private String text;
    private Long createUserId;
    private Long updateUserId;

    @Override
    public String toString() {
        return "BoardDTO{" +
                "text='" + text + '\'' +
                ", createUserId=" + createUserId +
                ", updateUserId=" + updateUserId +
                '}';
    }

    public static Board toUser(BoardDTO boardDTO) {
        return Board.builder()
                .text(boardDTO.getText())
                .createUserId(boardDTO.getCreateUserId())
                .updateUserId(boardDTO.getUpdateUserId())
                .build();
    }
}
