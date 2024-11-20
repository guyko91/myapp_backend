package com.myapp.playground.domain.calendar;

import com.myapp.playground.domain.calendar.dto.CalendarDto;
import com.myapp.playground.domain.calendar.dto.EventDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CalendarQueryService {

    public CalendarRepository calendarRepository;

    public CalendarDto getCalendarBy(long userId) {
        return CalendarDto.from(calendarRepository.getCalendarBy(userId));
    }

    public List<EventDto> getEventsBy(long calendarId, int year, int month) {
        return calendarRepository.getEventsBy(calendarId, year, month)
            .stream()
            .map(EventDto::from)
            .toList();
    }

}
