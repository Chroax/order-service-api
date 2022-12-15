package com.binar.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingResponse {

    private UUID bookingId;
    private LocalDateTime createdAt;
    private String email;
    private UUID schedulesId;
    private Integer totalSeat;
    private String message;
}
