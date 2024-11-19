package com.myapp.playground.domain.calendar;

import com.myapp.playground.domain.common.BaseTimeEntity;
import com.myapp.playground.domain.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_user_calendar")
public class Calendar extends BaseTimeEntity {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private User user;

    @Column(name = "external_calendar_id", nullable = false, unique = true)
    private String externalCalendarId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private CalendarStatus status = CalendarStatus.ACTIVE;

    protected Calendar() { }

    private Calendar(Long id, String externalCalendarId, User user, CalendarStatus status) {
        this.id = id;
        this.externalCalendarId = externalCalendarId;
        this.user = user;
        this.status = status;
    }

    public static Calendar createWith(User user, String externalCalendarId) {
        return new Calendar(null, externalCalendarId, user, CalendarStatus.ACTIVE);
    }

}
