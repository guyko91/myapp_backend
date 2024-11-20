package com.myapp.playground.application.calendar.dto.request;

public record UserCalendarEventListQuery(
    long userId,
    int year,
    int month
) {
    public static UserCalendarEventListQuery of(long userId, int year, int month) {
        return new UserCalendarEventListQuery(userId, year, month);
    }
}
