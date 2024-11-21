package com.myapp.playground.domain.calendar;

import com.myapp.playground.domain.calendar.entity.UserCalendar;
import com.myapp.playground.domain.calendar.entity.Event;
import java.util.List;

public interface CalendarRepository {

    UserCalendar save(UserCalendar userCalendar);
    UserCalendar getCalendarBy(long userId);
    List<Event> getEventsBy(long calendarId, int year, int month);

}
