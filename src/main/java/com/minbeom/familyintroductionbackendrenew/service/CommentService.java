package com.minbeom.familyintroductionbackendrenew.service;

import com.minbeom.familyintroductionbackendrenew.domain.Comment;
import com.minbeom.familyintroductionbackendrenew.dto.CommentDTO;
import com.minbeom.familyintroductionbackendrenew.repository.MysqlCommentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CommentService {
    private final MysqlCommentRepository mysqlCommentRepository;

    public CommentService(MysqlCommentRepository mysqlCommentRepository) {
        this.mysqlCommentRepository = mysqlCommentRepository;
    }

    public List<Comment> getComments(Long boardId) {
        List<Comment> commentList = mysqlCommentRepository.findById(boardId);
        for (Comment comment : commentList) {
            List<Comment> parentList = new ArrayList<>();
            for (Comment test : commentList) {
                if (comment.getId().equals(test.getParentId())) {
                    parentList.add(test);
                }
            }
            comment.getData().setReplies(parentList);
        }
//        comment.getParentId() == 0
        return commentList.stream().filter(comment -> comment.getParentId().equals(0L)).collect(Collectors.toList());
    }

    public Comment saveComment(CommentDTO commentDTO) {
        Comment comment = CommentDTO.toComment(commentDTO);
        mysqlCommentRepository.save(comment);
        return comment;
    }
}
