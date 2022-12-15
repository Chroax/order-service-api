package com.binar.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SeatResponse {

    private Integer seatId;
    private String seatNumber;
    private Integer seatCinemaHall;
    private String message;
}
