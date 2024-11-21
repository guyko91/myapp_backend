package com.myapp.playground.presentation.user.dto.response;

import com.myapp.playground.application.calendar.dto.response.UserCalendarEventListResult;
import java.util.List;

public record UserEventListApiResponse(
    List<UserEventResponse> events
) {
    public static UserEventListApiResponse from(UserCalendarEventListResult result) {
        return new UserEventListApiResponse(
            result.events().stream()
                .map(UserEventResponse::from)
                .toList()
        );
    }
}
