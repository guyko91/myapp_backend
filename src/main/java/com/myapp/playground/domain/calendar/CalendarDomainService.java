package com.myapp.playground.domain.calendar;

import com.myapp.playground.domain.calendar.entity.UserCalendar;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CalendarDomainService {

    private final ExternalCalendarClient externalCalendarClient;
    private final CalendarRepository calendarRepository;

    public UserCalendar createUserCalendar(long userId, String userEmail) {
        UserCalendar createdCalendar = externalCalendarClient.createNewCalendarAndShareWith(userId, userEmail);
        return calendarRepository.save(createdCalendar);
    }

}
