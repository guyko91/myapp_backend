package com.myapp.playground.infrastructure.db.jpa.calendar;

import com.myapp.playground.domain.calendar.CalendarRepository;
import com.myapp.playground.domain.calendar.entity.Calendar;
import com.myapp.playground.domain.calendar.entity.Event;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CalendarRepositoryAdapter implements CalendarRepository {

    private final CalendarJpaRepository calendarJpaRepository;
    private final EventJpaRepository eventJpaRepository;

    @Override
    public Calendar getCalendarBy(long userId) {
        return calendarJpaRepository.findByUserId(userId)
            .orElseThrow(() -> new IllegalArgumentException("Calendar not found"));
    }

    @Override
    public List<Event> getEventsBy(long calendarId, int year, int month) {

        LocalDateTime start = LocalDateTime.of(year, month, 1, 0, 0, 0);
        LocalDateTime end = start.plusMonths(1).minusDays(1);

        return eventJpaRepository.findEventsByCalendarIdAndStartDateTimeBetween(calendarId, start, end);
    }
}
