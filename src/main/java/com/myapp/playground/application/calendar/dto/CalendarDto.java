package com.myapp.playground.application.calendar.dto;

import com.myapp.playground.domain.calendar.entity.UserCalendar;

public record CalendarDto(
    long id,
    String extCalendarId,
    String status
) {
    public static CalendarDto from(UserCalendar userCalendar) {
        return new CalendarDto(
            userCalendar.getId(),
            userCalendar.getExternalCalendarId(),
            userCalendar.getStatus().name()
        );
    }
}
