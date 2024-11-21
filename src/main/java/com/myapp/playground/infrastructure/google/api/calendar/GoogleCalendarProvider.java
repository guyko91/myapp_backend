package com.myapp.playground.infrastructure.google.api.calendar;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.calendar.model.AclRule;
import com.google.api.services.calendar.model.Calendar;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.myapp.playground.domain.calendar.ExternalCalendarClient;
import com.myapp.playground.domain.calendar.entity.UserCalendar;
import com.myapp.playground.infrastructure.google.exception.GoogleCalendarException;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class GoogleCalendarProvider implements ExternalCalendarClient {

    private static final String APPLICATION_NAME = "KidsCare POC Application";
    private static final String CREDENTIALS_FILE_PATH = "/service-account.json";
    private static final String MASTER_ACCOUNT_EMAIL = "MASTER_ACCOUNT_EMAIL";

    private static final String CALENDAR_ROLE_OWNER = "owner";
    private static final String CALENDAR_ROLE_WRITER = "writer";

    private static final Set<String> SCOPES = Collections.singleton("https://www.googleapis.com/auth/calendar");

    private final com.google.api.services.calendar.Calendar calendarService;

    public GoogleCalendarProvider() throws IOException, GeneralSecurityException {
        InputStream in = GoogleCalendarProvider.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new IOException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }

        GoogleCredentials credentials = ServiceAccountCredentials.fromStream(in).createScoped(SCOPES);

        this.calendarService = new com.google.api.services.calendar.Calendar.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                GsonFactory.getDefaultInstance(),
                new HttpCredentialsAdapter(credentials)
            ).setApplicationName(APPLICATION_NAME)
            .build();
    }

    @Override
    public UserCalendar createNewCalendarAndShareWith(long userId, String userEmail) {
        Calendar calendar;
        try {
            calendar = createNewCalendar(String.valueOf(userId));
            shareCalendar(calendar.getId(), CALENDAR_ROLE_OWNER, userEmail);
        }catch (IOException e) {
            e.printStackTrace();
            throw new GoogleCalendarException("Failed to create a new calendar for user: " + userId);
        }

        return UserCalendar.createWith(userId, calendar.getId());
    }
    
    private Calendar createNewCalendar(String summary) throws IOException {
        Calendar calendar = new Calendar();
        calendar.setSummary(summary);
        calendar.setTimeZone("Asia/Seoul");

        Calendar createdCalendar = calendarService.calendars().insert(calendar).execute();
        shareCalendar(createdCalendar.getId(), CALENDAR_ROLE_WRITER, MASTER_ACCOUNT_EMAIL);

        return createdCalendar;
    }

    private void shareCalendar(String calendarId, String role, String userEmail) throws IOException {
        AclRule rule = new AclRule()
            .setScope(new AclRule.Scope().setType("user").setValue(userEmail))
            .setRole(role);
        calendarService.acl().insert(calendarId, rule).execute();
    }
}
