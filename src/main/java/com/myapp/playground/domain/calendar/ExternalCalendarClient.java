package com.myapp.playground.domain.calendar;

import com.myapp.playground.domain.calendar.entity.UserCalendar;

public interface ExternalCalendarClient {

    UserCalendar createNewCalendarAndShareWith(long userId, String email);

}
