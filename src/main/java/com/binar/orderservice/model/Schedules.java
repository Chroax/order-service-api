package com.binar.orderservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "schedules")
public class Schedules {

    @Id
    @GeneratedValue
    @Column(name = "schedule_id")
    private UUID scheduleId;

    @Column(name = "start_time", nullable = false, columnDefinition="TIME WITH TIME ZONE")
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false, columnDefinition="TIME WITH TIME ZONE")
    private LocalTime endTime;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+7")
    @Column(name = "date", nullable = false, columnDefinition="DATE")
    private LocalDate date;

    @Column(name = "price", nullable = false, precision = 2)
    private Float price;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
    @Column(name = "created_at", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
    @Column(name = "modified_at")
    @UpdateTimestamp
    private LocalDateTime modifiedAt;

    @Column(name = "film_name", nullable = false)
    private String filmsName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cinema_hall_id", nullable = false)
    private CinemaHall cinemaHall;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "schedulesBook", cascade = CascadeType.ALL)
    private Set<Booking> booking;
}
