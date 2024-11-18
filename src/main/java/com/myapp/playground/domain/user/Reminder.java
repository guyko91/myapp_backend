package com.myapp.playground.domain.user;

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

@Entity
@Table(name = "tb_user_calendar_event_reminder")
public class Reminder extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reminder_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Column(name = "minutes_before", nullable = false)
    private Integer minutesBefore;

    protected Reminder() {}

    private Reminder(Long id, Event event, Integer minutesBefore) {
        this.id = id;
        this.event = event;
        this.minutesBefore = minutesBefore;
    }

    public void linkEvent(Event event) {
        this.event = event;
    }

    public static Reminder createFrom(Event event, int minutesBefore) {
        Reminder reminder = new Reminder(null, null, minutesBefore);
        reminder.linkEvent(event);
        return reminder;
    }

}
