package com.myapp.playground.presentation.calendar;

import com.myapp.playground.application.calendar.CalendarApplication;
import com.myapp.playground.application.calendar.dto.request.UserCalendarEventListQuery;
import com.myapp.playground.application.calendar.dto.response.UserCalendarEventListResult;
import com.myapp.playground.presentation.calendar.dto.response.UserEventListApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calendar")
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarApplication calendarApplication;

    @GetMapping("events")
    public ResponseEntity<UserEventListApiResponse> eventList(
        @RequestParam("userId") long userId,
        @RequestParam("year") int year,
        @RequestParam("month") int month) {

        UserCalendarEventListQuery query = UserCalendarEventListQuery.of(userId, year, month);
        UserCalendarEventListResult result = calendarApplication.getUserCalendarEvents(query);

        return ResponseEntity.ok(UserEventListApiResponse.from(result));
    }

}
