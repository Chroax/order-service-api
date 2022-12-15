package com.binar.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingDetailResponse {
    private UUID bookingId;
    private Integer seatId;
    private Boolean status;
    private String message;
}
