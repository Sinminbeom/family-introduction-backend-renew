package com.minbeom.familyintroductionbackendrenew.repository;

import com.minbeom.familyintroductionbackendrenew.domain.Board;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

public class MysqlBoardRepository extends MysqlRepositoryBase{
    public MysqlBoardRepository(DataSource dataSource) {
        super(dataSource);
    }

    public Board update(Long boardId, Board board) {
        String sql = "update Board set title = ?, text = ?, updateUserId = ?, updateTime = now() where id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, board.getTitle());
            pstmt.setString(2, board.getText());
            pstmt.setLong(3, board.getUpdateUserId());
            pstmt.setLong(4, boardId);
            pstmt.executeUpdate();
            return board;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    public Board create(Board board) {
        String sql = "insert into Board(title, text, createUserId, createTime, updateUserId, updateTime) values(?, ?, ?, now(), ?, now())";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, board.getTitle());
            pstmt.setString(2, board.getText());
            pstmt.setLong(3, board.getCreateUserId());
            pstmt.setLong(4, board.getUpdateUserId());
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                board.setId(rs.getLong(1));
            } else {
                throw new SQLException("id 조회 실패");
            }
            return board;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    public List<Board> findAll() {
        String sql =    "SELECT A.*, B.name AS createUserName, B.email AS userEmail, B.avatar AS userAvatar" +
                        "  FROM Board AS A " +
                        "  LEFT OUTER JOIN User  AS B ON A.createUserId = B.id";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            List<Board> boards = new ArrayList<>();
            while(rs.next()) {
                Board board = new Board();
                board.setId(rs.getLong("id"));
                board.setTitle(rs.getString("title"));
                board.setText(rs.getString("text"));
                board.setCreateUserName(rs.getString("createUserName"));
                board.setUserEmail(rs.getString("userEmail"));
                board.setUserAvatar(rs.getString("userAvatar"));
                boards.add(board);
            }
            return boards;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    public Optional<Board> findById(Long boardId) {
        String sql = "select * from Board where id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, boardId);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                Board board = new Board();
                board.setId(rs.getLong("id"));
                board.setTitle(rs.getString("title"));
                board.setText(rs.getString("text"));
                board.setCreateUserId(rs.getLong("createUserId"));
//                board.setName(rs.getString("name"));
//                board.setPassword(rs.getString("password"));
//                board.setEmail(rs.getString("email"));
                return Optional.of(board);
            }
            return Optional.empty();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    public int delete(Long boardId) {
        String sql = "DELETE FROM Board WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, boardId);
            int deletedRow = pstmt.executeUpdate();
            return deletedRow;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

}
