package com.myapp.playground.domain.calendar;

import com.myapp.playground.domain.common.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_user_calendar_event_reminder")
public class Reminder extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reminder_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Column(name = "reminder_date_time", nullable = false)
    private LocalDateTime reminderDateTime;

    protected Reminder() {}

    private Reminder(Long id, Event event, LocalDateTime reminderDateTime) {
        this.id = id;
        this.event = event;
        this.reminderDateTime = reminderDateTime;
    }

    public static Reminder createFrom(Event event, LocalDateTime reminderDateTime) {
        return new Reminder(null, event, reminderDateTime);
    }

}
