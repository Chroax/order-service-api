package com.binar.orderservice.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.UUID;


@Data
public class BookingRequest {

    @NotEmpty(message = "Email is required.")
    private String email;

    @NotEmpty(message = "Schedule is required.")
    private UUID schedulesId;

    @NotEmpty(message = "Total seat is required.")
    private Integer totalSeat;
}
