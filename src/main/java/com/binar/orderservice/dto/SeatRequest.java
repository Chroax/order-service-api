package com.binar.orderservice.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;


@Data
public class SeatRequest {

    @NotEmpty(message = "Seat number is required.")
    private String seatNumber;

    private Integer cinemaHallId;
}
