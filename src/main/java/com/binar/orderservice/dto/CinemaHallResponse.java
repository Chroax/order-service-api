package com.binar.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CinemaHallResponse {

    private Integer cinemaHallId;
    private String cinemaHallName;
    private String message;
}
