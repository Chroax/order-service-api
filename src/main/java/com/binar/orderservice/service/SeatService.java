package com.binar.orderservice.service;

import com.binar.orderservice.dto.SeatRequest;
import com.binar.orderservice.dto.SeatResponse;

import java.util.List;

public interface SeatService {

    SeatResponse addSeat(SeatRequest seatRequest);
    List<SeatResponse> showAllSeatByCinemaHall(Integer cinemaHallId);
}
