package com.myapp.playground.application.calendar.dto.response;

import com.myapp.playground.application.calendar.dto.EventDto;
import com.myapp.playground.domain.calendar.entity.Event;
import java.util.List;

public record UserCalendarEventListResult(
    List<EventDto> events
) {
    public static UserCalendarEventListResult from(List<Event> domainEvents) {
        return new UserCalendarEventListResult(
            domainEvents.stream()
                .map(EventDto::from)
                .toList()
        );
    }
}
