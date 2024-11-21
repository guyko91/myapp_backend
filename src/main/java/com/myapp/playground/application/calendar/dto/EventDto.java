package com.myapp.playground.application.calendar.dto;

import com.myapp.playground.domain.calendar.entity.Event;
import java.time.LocalDateTime;
import java.util.List;

public record EventDto(
    long eventId,
    String title,
    String type,
    boolean allDay,
    LocalDateTime startDateTime,
    LocalDateTime endDateTime,
    String repeatCycle,
    String memo,
    List<LocalDateTime> remindDateTimes
) {

    public static EventDto from(Event event) {
        return new EventDto(
            event.getId(),
            event.getTitle(),
            event.getType().name(),
            event.isAllDay(),
            event.getStartDateTime(),
            event.getEndDateTime(),
            event.getRepeatCycle().name(),
            event.getMemo(),
            event.getReminderDateTimes()
        );
    }

}
