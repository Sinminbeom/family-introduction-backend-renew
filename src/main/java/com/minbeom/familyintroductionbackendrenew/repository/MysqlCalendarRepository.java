package com.minbeom.familyintroductionbackendrenew.repository;

import com.minbeom.familyintroductionbackendrenew.domain.Calendar;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysqlCalendarRepository extends MysqlRepositoryBase {
    public MysqlCalendarRepository(DataSource dataSource) {
        super(dataSource);
    }

    public Calendar create(Calendar calendar) {
        String sql = "insert into Calendar(title, description, start, end, allDay, color, textColor, createUserId, createTime, updateUserId, updateTime) values(?, ?, ?, ?, ?, ?, ?, ?, now(), ?, now())";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, calendar.getTitle());
            pstmt.setString(2, calendar.getDescription());
            pstmt.setTimestamp(3, calendar.getStart());
            pstmt.setTimestamp(4, calendar.getEnd());
            pstmt.setBoolean(5, calendar.getAllDay());
            pstmt.setString(6, calendar.getColor());
            pstmt.setString(7, calendar.getTextColor());
            pstmt.setLong(8, calendar.getCreateUserId());
            pstmt.setLong(9, calendar.getUpdateUserId());
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                calendar.setId(rs.getLong(1));
            } else {
                throw new SQLException("id 조회 실패");
            }
            return calendar;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    public List<Calendar> findCalendar(Long userId) {
        String sql = "SELECT * FROM Calendar WHERE createUserId = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, userId);
            rs = pstmt.executeQuery();
            List<Calendar> calendarList = new ArrayList<>();
            while(rs.next()) {
                Calendar calendar = new Calendar();
                calendar.setId(rs.getLong("id"));
                calendar.setTitle(rs.getString("title"));
                calendar.setDescription(rs.getString("description"));
                calendar.setStart(rs.getTimestamp("start"));
                calendar.setEnd(rs.getTimestamp("end"));
                calendar.setAllDay(rs.getBoolean("allDay"));
                calendar.setColor(rs.getString("color"));
                calendar.setTextColor(rs.getString("textColor"));
                calendarList.add(calendar);
            }
            return calendarList;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    public int deleteCalendar(Long calendarId) {
        String sql = "DELETE FROM Calendar WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, calendarId);
            int deletedRow = pstmt.executeUpdate();
            return deletedRow;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    public Calendar updateCalendar(Calendar calendar) {
        String sql = "update Calendar set title = ?, description = ?, start = ?, end = ?, allDay = ?, color = ?, textColor = ?, updateUserId = ?, updateTime = now() where id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, calendar.getTitle());
            pstmt.setString(2, calendar.getDescription());
            pstmt.setTimestamp(3, calendar.getStart());
            pstmt.setTimestamp(4, calendar.getEnd());
            pstmt.setBoolean(5, calendar.getAllDay());
            pstmt.setString(6, calendar.getColor());
            pstmt.setString(7, calendar.getTextColor());
            pstmt.setLong(8, calendar.getUpdateUserId());
            pstmt.setLong(9, calendar.getId());
            pstmt.executeUpdate();
            return calendar;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }
}
