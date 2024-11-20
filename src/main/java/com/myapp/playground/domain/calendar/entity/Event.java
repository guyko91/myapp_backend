package com.myapp.playground.domain.calendar.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Entity @Getter
@Table(name = "tb_user_calendar_event")
public class Event {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "calendar_id", nullable = false)
    private Long calendarId;

    @Column(name = "title", nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private EventType type;

    @Column(name = "is_all_day", nullable = false, columnDefinition = "TINYINT")
    private boolean allDay;

    @Column(name = "start_date_time", nullable = false)
    private LocalDateTime startDateTime;

    @Column(name = "end_date_time")
    private LocalDateTime endDateTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "repeat_cycle", nullable = false)
    private RepeatCycle repeatCycle = RepeatCycle.NONE;

    @Column(name = "memo")
    private String memo;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reminder> reminders = new ArrayList<>();

    protected Event() {}

    private Event(Long id, String title, EventType type, boolean allDay,
        LocalDateTime startDateTime,
        LocalDateTime endDateTime, RepeatCycle repeatCycle, String memo, List<Reminder> reminders) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.allDay = allDay;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.repeatCycle = repeatCycle;
        this.memo = memo;
        this.reminders = reminders;
    }

    public static Event createOf(String title, EventType type, boolean allDay,
        LocalDateTime startDateTime, LocalDateTime endDateTime, RepeatCycle repeatCycle, String memo) {
        return new Event(null, title, type, allDay, startDateTime, endDateTime, repeatCycle, memo, new ArrayList<>());
    }

    public void addReminders(int[] minutesBefore) {
        for (int minutes : minutesBefore) {
            LocalDateTime reminderDateTime = this.startDateTime.minusMinutes(minutes);
            Reminder reminder = Reminder.createFrom(this, reminderDateTime);
            this.reminders.add(reminder);
        }
    }

    public List<LocalDateTime> getReminderDateTimes() {
        return reminders.stream()
            .map(Reminder::getReminderDateTime)
            .toList();
    }

}
