package com.minbeom.familyintroductionbackendrenew.controller;

import com.minbeom.familyintroductionbackendrenew.domain.Board;
import com.minbeom.familyintroductionbackendrenew.domain.Calendar;
import com.minbeom.familyintroductionbackendrenew.dto.BoardDTO;
import com.minbeom.familyintroductionbackendrenew.dto.CalendarDTO;
import com.minbeom.familyintroductionbackendrenew.exception.InvalidParameterException;
import com.minbeom.familyintroductionbackendrenew.response.Response;
import com.minbeom.familyintroductionbackendrenew.service.CalendarService;
import org.springframework.dao.DataAccessException;
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
public class CalendarController {
    private final CalendarService calendarService;

    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @PutMapping("/calendar/{calendarId}")
    public ResponseEntity<Response> updateCalendar(@Valid @RequestBody CalendarDTO calendarDTO, BindingResult result, @PathVariable Long calendarId) {
        Calendar calendar = calendarService.updateCalendar(calendarDTO, calendarId);
        Response response = new Response(200, calendar);
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("utf-8")));
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
    @PostMapping("/calendar")
    public ResponseEntity<Response> createCalendar(@Valid @RequestBody CalendarDTO calendarDTO, BindingResult result) {
        if (result.hasErrors()) {
            throw new InvalidParameterException(result);
        }
        System.out.println(calendarDTO.toString());
        Calendar calendar = calendarService.create(calendarDTO);
        Response response = new Response(200, calendar);
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("utf-8")));
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @GetMapping("/calendar/{userId}")
    public ResponseEntity<Response> findCalendar(@PathVariable Long userId) {
        List<Calendar> calendarList = calendarService.findCalendar(userId);
        Response response = new Response(200, calendarList);
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("utf-8")));
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @DeleteMapping("/calendar/{calendarId}")
    public ResponseEntity<Response> deleteCalendar(@PathVariable Long calendarId) {
        int deleteRow = calendarService.deleteCalendar(calendarId);
        if (deleteRow == 0) {
            throw new DataAccessException("Calendar 삭제에 실패했습니다.") {};
        }
        Response response = new Response(200, deleteRow);
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("utf-8")));
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
}
