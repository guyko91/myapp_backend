package com.myapp.playground.infrastructure.db.jpa.calendar;

import com.myapp.playground.domain.calendar.entity.UserCalendar;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarJpaRepository extends JpaRepository<UserCalendar, Long> {

    Optional<UserCalendar> findByUserId(long userId);

}
