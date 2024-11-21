package com.myapp.playground.domain.calendar;

import com.myapp.playground.domain.calendar.entity.UserCalendar;
import com.myapp.playground.domain.calendar.entity.Event;
import com.myapp.playground.global.http.exception.NotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CalendarQueryService {

    public CalendarRepository calendarRepository;

    public List<Event> getUserEventsBy(long userId, int year, int month) {
        UserCalendar userCalendar = calendarRepository.getCalendarBy(userId);

        if (userCalendar == null) {
            throw new NotFoundException("Calendar not found for user: " + userId);
        }

        long calendarId = userCalendar.getId();
        return calendarRepository.getEventsBy(calendarId, year, month);
    }

}
