package com.minbeom.familyintroductionbackendrenew.service;

import com.minbeom.familyintroductionbackendrenew.domain.Board;
import com.minbeom.familyintroductionbackendrenew.domain.Comment;
import com.minbeom.familyintroductionbackendrenew.dto.BoardDTO;
import com.minbeom.familyintroductionbackendrenew.repository.MysqlBoardRepository;

import java.util.List;

public class BoardService {
    private final MysqlBoardRepository mysqlBoardRepository;

    public BoardService(MysqlBoardRepository mysqlBoardRepository) {
        this.mysqlBoardRepository = mysqlBoardRepository;
    }
    public Board create(BoardDTO boardDTO) {
        Board board = BoardDTO.toUser(boardDTO);
//        validateDuplicateBoard(board);
        mysqlBoardRepository.create(board);
        return board;
    }

    private void validateDuplicateBoard(Board board) {
    }

    public Board update(BoardDTO boardDTO) {
        return null;
    }

    public Board get(Long boardId) {
        Board board = mysqlBoardRepository.findById(boardId).get();
        return board;
    }

    public Board save(BoardDTO boardDTO) {
        Board board = BoardDTO.toUser(boardDTO);
//        validateDuplicateBoard(board);
        mysqlBoardRepository.create(board);
        return board;
    }

    public List<Board> findBoards() {
        return mysqlBoardRepository.findAll();
    }

    public int delete(Long boardId) {
        int deleteRow = mysqlBoardRepository.delete(boardId);
        return deleteRow;
    }

}
