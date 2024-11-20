package com.myapp.playground.infrastructure.db.jpa.calendar;

import com.myapp.playground.domain.calendar.entity.Calendar;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarJpaRepository extends JpaRepository<Calendar, Long> {

    Optional<Calendar> findByUserId(long userId);

}
