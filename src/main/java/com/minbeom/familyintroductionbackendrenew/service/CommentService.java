package com.minbeom.familyintroductionbackendrenew.service;

import com.minbeom.familyintroductionbackendrenew.domain.Comment;
import com.minbeom.familyintroductionbackendrenew.dto.CommentDTO;
import com.minbeom.familyintroductionbackendrenew.repository.MysqlCommentRepository;
import org.springframework.dao.DataAccessException;

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

    public Comment saveComment(Long boardId, CommentDTO commentDTO) {
        Comment comment = CommentDTO.toComment(commentDTO);
        mysqlCommentRepository.save(boardId, comment);
        return comment;
    }

    public int deleteComment(Long commentId) {
        int deleteRow = mysqlCommentRepository.delete(commentId);
        if (deleteRow == 0) {
            throw new DataAccessException("comment 삭제에 실패했습니다.") {};
        }
        return deleteRow;
    }

    public Comment saveReply(Long boardId, Long parentId, CommentDTO commentDTO) {
        Comment comment = CommentDTO.toComment(commentDTO);
        comment.setParentId(parentId);
        mysqlCommentRepository.saveReply(boardId, parentId, comment);
        return comment;
    }
}
