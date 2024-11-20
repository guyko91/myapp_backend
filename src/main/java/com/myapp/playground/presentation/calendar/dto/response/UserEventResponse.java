package com.myapp.playground.presentation.calendar.dto.response;

import com.myapp.playground.domain.calendar.dto.EventDto;
import java.time.LocalDateTime;
import java.util.List;

public record UserEventResponse(
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
    public static UserEventResponse from(EventDto eventDto) {
        return new UserEventResponse(
            eventDto.eventId(),
            eventDto.title(),
            eventDto.type(),
            eventDto.allDay(),
            eventDto.startDateTime(),
            eventDto.endDateTime(),
            eventDto.repeatCycle(),
            eventDto.memo(),
            eventDto.remindDateTimes()
        );
    }
}
