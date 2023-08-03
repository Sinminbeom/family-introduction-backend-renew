package com.minbeom.familyintroductionbackendrenew.dto;

import com.minbeom.familyintroductionbackendrenew.domain.Calendar;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class CalendarDTO {
	private String title;
    private String description;
    private Timestamp start;
	private Timestamp end;
	private Boolean allDay;
	private String color;
	private String textColor;
	private Long createUserId;
	private Long updateUserId;

	public static Calendar toCalendar(CalendarDTO calendarDTO) {
		return Calendar.builder().title(calendarDTO.getTitle())
				.description(calendarDTO.getDescription())
				.start(calendarDTO.getStart())
				.end(calendarDTO.getEnd())
				.allDay(calendarDTO.getAllDay())
				.color(calendarDTO.getColor())
				.textColor(calendarDTO.getTextColor())
				.createUserId(calendarDTO.getCreateUserId())
				.updateUserId(calendarDTO.getUpdateUserId()).build();
	}

	@Override
	public String toString() {
		return "CalendarDTO{" +
				"title='" + title + '\'' +
				", description='" + description + '\'' +
				", start=" + start +
				", end=" + end +
				", allDay=" + allDay +
				", color='" + color + '\'' +
				", textColor='" + textColor + '\'' +
				", createUserId=" + createUserId +
				", updateUserId=" + updateUserId +
				'}';
	}
}
