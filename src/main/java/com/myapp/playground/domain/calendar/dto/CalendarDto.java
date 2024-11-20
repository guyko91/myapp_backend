package com.myapp.playground.domain.calendar.dto;

import com.myapp.playground.domain.calendar.entity.Calendar;

public record CalendarDto(
    long id,
    String extCalendarId,
    String status
) {
    public static CalendarDto from(Calendar calendar) {
        return new CalendarDto(
            calendar.getId(),
            calendar.getExternalCalendarId(),
            calendar.getStatus().name()
        );
    }
}
