package com.minbeom.familyintroductionbackendrenew.controller;

import com.minbeom.familyintroductionbackendrenew.domain.Comment;
import com.minbeom.familyintroductionbackendrenew.dto.BoardDTO;
import com.minbeom.familyintroductionbackendrenew.dto.CommentDTO;
import com.minbeom.familyintroductionbackendrenew.response.Response;
import com.minbeom.familyintroductionbackendrenew.service.CommentService;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.List;

@RestController
@CrossOrigin(origins = "*") // 허용할 출처를 설정합니다.
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/boards/{boardId}/comments")
    public ResponseEntity<Response> getComments(@PathVariable Long boardId) {
        List<Comment> commentList = commentService.getComments(boardId);

        Response response = new Response(200, commentList);

        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("utf-8")));

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @PostMapping("/comments")
    public ResponseEntity<Response> saveComment(@RequestBody CommentDTO commentDTO) {
        Comment comment = commentService.saveComment(commentDTO);
        Response response = new Response(200, comment);

        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("utf-8")));
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Response> deleteComment(@PathVariable Long commentId) {
        int deleteRow = commentService.deleteComment(commentId);
        if (deleteRow == 0) {
            throw new DataAccessException("comment 삭제에 실패했습니다.") {};
        }
        Response response = new Response(200, deleteRow);

        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("utf-8")));
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
}
