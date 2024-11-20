package com.myapp.playground.application.calendar;

import com.myapp.playground.application.calendar.dto.request.UserCalendarEventListQuery;
import com.myapp.playground.application.calendar.dto.response.UserCalendarEventListResult;
import com.myapp.playground.domain.calendar.CalendarQueryService;
import com.myapp.playground.domain.calendar.dto.CalendarDto;
import com.myapp.playground.domain.calendar.dto.EventDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CalendarApplication {

    private final CalendarQueryService calendarQueryService;

    @Transactional(readOnly = true)
    public UserCalendarEventListResult getUserCalendarEvents(UserCalendarEventListQuery query) {

        long userId = query.userId();
        CalendarDto userCalendar = calendarQueryService.getCalendarBy(userId);

        long calendarId = userCalendar.id();
        int year = query.year();
        int month = query.month();
        List<EventDto> eventList = calendarQueryService.getEventsBy(calendarId, year, month);

        return new UserCalendarEventListResult(eventList);
    }

}
