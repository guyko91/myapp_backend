package com.myapp.playground.infrastructure.db.jpa.calendar;

import com.myapp.playground.domain.calendar.entity.Event;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventJpaRepository extends JpaRepository<Event, Long> {

    List<Event> findEventsByCalendarIdAndStartDateTimeBetween(long calendarId, LocalDateTime start, LocalDateTime end);

}
