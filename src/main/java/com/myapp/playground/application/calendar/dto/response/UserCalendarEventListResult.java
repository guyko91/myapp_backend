package com.myapp.playground.application.calendar.dto.response;

import com.myapp.playground.domain.calendar.dto.EventDto;
import java.util.List;

public record UserCalendarEventListResult(
    List<EventDto> events
) { }
