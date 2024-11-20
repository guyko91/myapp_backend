package com.myapp.playground.domain.calendar;

import com.myapp.playground.domain.calendar.entity.Calendar;
import com.myapp.playground.domain.calendar.entity.Event;
import java.util.List;

public interface CalendarRepository {

    Calendar getCalendarBy(long userId);
    List<Event> getEventsBy(long calendarId, int year, int month);

}
