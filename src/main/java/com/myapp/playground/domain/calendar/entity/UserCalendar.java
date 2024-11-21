package com.myapp.playground.domain.calendar.entity;

import com.myapp.playground.domain.common.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity @Getter
@Table(name = "tb_user_calendar")
public class UserCalendar extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "external_calendar_id", nullable = false, unique = true)
    private String externalCalendarId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private CalendarStatus status = CalendarStatus.ACTIVE;

    protected UserCalendar() { }

    private UserCalendar(Long id, Long userId, String externalCalendarId, CalendarStatus status) {
        this.id = id;
        this.externalCalendarId = externalCalendarId;
        this.userId = userId;
        this.status = status;
    }

    public static UserCalendar createWith(Long userId, String externalCalendarId) {
        return new UserCalendar(null, userId, externalCalendarId, CalendarStatus.ACTIVE);
    }

}
