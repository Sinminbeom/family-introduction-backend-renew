package com.minbeom.familyintroductionbackendrenew.controller;

import com.minbeom.familyintroductionbackendrenew.domain.Board;
import com.minbeom.familyintroductionbackendrenew.dto.BoardDTO;
import com.minbeom.familyintroductionbackendrenew.exception.InvalidParameterException;
import com.minbeom.familyintroductionbackendrenew.response.Response;
import com.minbeom.familyintroductionbackendrenew.service.BoardService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.nio.charset.Charset;
import java.util.List;

@RestController
@CrossOrigin(origins = "*") // 허용할 출처를 설정합니다.
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("/board")
    public ResponseEntity<Response> createBoard(@Valid @RequestBody BoardDTO boardDTO, BindingResult result) {
        if (result.hasErrors()) {
            throw new InvalidParameterException(result);
        }
        System.out.println(boardDTO.toString());
        Board board = boardService.create(boardDTO);

        Response response = new Response(200, board);

        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("utf-8")));

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @GetMapping("/board/{boardId}")
    public ResponseEntity<Response> getBoard(@PathVariable Long boardId) {
        Board board = boardService.get(boardId);

        Response response = new Response(200, board);

        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("utf-8")));

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @PutMapping("/board/{boardId}")
    public ResponseEntity<Response> updateBoard(@Valid @RequestBody BoardDTO boardDTO, BindingResult result, @PathVariable Long boardId) {
        if (result.hasErrors()) {
            throw new InvalidParameterException(result);
        }

        Board board = boardService.update(boardDTO);
        Response response = new Response(200, board);

        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("utf-8")));

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @GetMapping("/boards")
    public ResponseEntity<Response> getBoards() {
        List<Board> boards = boardService.findBoards();

        Response response = new Response(200, boards);

        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("utf-8")));

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

}
