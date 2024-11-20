package com.myapp.playground.infrastructure.google.api.calendar;

import com.myapp.playground.domain.calendar.ExternalCalendarUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class GoogleCalendarProvider implements ExternalCalendarUseCase {

    private final WebClient googleApiWebClient;

}
