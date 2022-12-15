package com.binar.orderservice.dto;

import com.binar.orderservice.model.CinemaHall;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CinemaHallRequest {

    @NotEmpty(message = "Cinema hall name is required.")
    private String cinemaHallName;

    public CinemaHall toCinemaHall() {
        return CinemaHall.builder()
                .cinemaHallName(this.cinemaHallName)
                .build();
    }
}
