package com.minbeom.familyintroductionbackendrenew.service;

import com.minbeom.familyintroductionbackendrenew.domain.Calendar;
import com.minbeom.familyintroductionbackendrenew.dto.CalendarDTO;
import com.minbeom.familyintroductionbackendrenew.repository.MysqlCalendarRepository;

import java.util.List;

public class CalendarService {
    private final MysqlCalendarRepository mysqlCalendarRepository;

    public CalendarService(MysqlCalendarRepository mysqlCalendarRepository) {
        this.mysqlCalendarRepository = mysqlCalendarRepository;
    }

    public Calendar create(CalendarDTO calendarDTO) {
        Calendar calendar = CalendarDTO.toCalendar(calendarDTO);
        mysqlCalendarRepository.create(calendar);
        return calendar;
    }

    public List<Calendar> findCalendar(Long userId) {
        List<Calendar> calendarList = mysqlCalendarRepository.findCalendar(userId);
        return calendarList;
    }

    public int deleteCalendar(Long calendarId) {
        int deleteRow = mysqlCalendarRepository.deleteCalendar(calendarId);
        return deleteRow;
    }

    public Calendar updateCalendar(CalendarDTO calendarDTO, Long calendarId) {
        Calendar calendar = CalendarDTO.toCalendar(calendarDTO);
        calendar.setId(calendarId);
        return mysqlCalendarRepository.updateCalendar(calendar);
    }
}
