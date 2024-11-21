package com.myapp.playground.application.calendar;

import com.myapp.playground.application.calendar.dto.request.UserCalendarEventListQuery;
import com.myapp.playground.application.calendar.dto.response.UserCalendarEventListResult;
import com.myapp.playground.domain.calendar.CalendarQueryService;
import com.myapp.playground.domain.calendar.entity.Event;
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
        List<Event> userEvents = calendarQueryService.getUserEventsBy(
            query.userId(),
            query.year(),
            query.month()
        );
        return UserCalendarEventListResult.from(userEvents);
    }

}
