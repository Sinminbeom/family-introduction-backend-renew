package com.minbeom.familyintroductionbackendrenew.repository;

import com.minbeom.familyintroductionbackendrenew.domain.Comment;
import com.minbeom.familyintroductionbackendrenew.domain.Data;
import com.minbeom.familyintroductionbackendrenew.domain.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MysqlCommentRepository extends MysqlRepositoryBase{

    public MysqlCommentRepository(DataSource dataSource) {
        super(dataSource);
    }

    public List<Comment> findById(Long boardId) {
        String sql = "SELECT A.*, B.id AS userId, B.name AS userName\n" +
                     "from Comment AS A\n" +
                     "LEFT OUTER JOIN User AS B ON A.createUserId = B.id\n" +
                     "WHERE boardId = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, boardId);
            rs = pstmt.executeQuery();
            List<Comment> commentList = new ArrayList<>();
            Map<Long, Comment> commentMap = new HashMap<>();
            while(rs.next()) {
                User user = new User();
                user.setId(rs.getLong("userId"));
                user.setName(rs.getString("userName"));

                Data data = new Data();
                data.setComment(rs.getString("text"));

                Comment comment = new Comment();
                comment.setId(rs.getLong("id"));
                comment.setProfile(user);
                comment.setData(data);
                comment.setParentId(rs.getLong("parentId"));
                commentList.add(comment);
            }
            return commentList;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    public Comment save(Comment comment) {
        String sql = "insert into Comment(parentId, boardId, text, createUserId, createTime, updateUserId, updateTime) values(0, ?, ?, ?, now(), ?, now())";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setLong(1, comment.getBoardId());
            pstmt.setString(2, comment.getData().getComment());
            pstmt.setLong(3, comment.getProfile().getId());
            pstmt.setLong(4, comment.getProfile().getId());
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                comment.setId(rs.getLong(1));
            } else {
                throw new SQLException("id 조회 실패");
            }
            return comment;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    public int delete(Long commentId) {
        String sql = "DELETE FROM Comment WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, commentId);
            int deletedRow = pstmt.executeUpdate();
            return deletedRow;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }
}
