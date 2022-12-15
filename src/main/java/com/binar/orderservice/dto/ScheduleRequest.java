package com.binar.orderservice.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ScheduleRequest {

    @NotEmpty(message = "Start Time is required.")
    private LocalTime startTime;

    @NotEmpty(message = "End Time is required.")
    private LocalTime endTime;

    @NotEmpty(message = "Date is required.")
    private LocalDate date;

    @NotEmpty(message = "Price is required.")
    private Float price;

    @NotEmpty(message = "Film name is required.")
    private String filmName;

    @NotEmpty(message = "Cinema Hall is required.")
    private Integer cinemaHallId;
}
