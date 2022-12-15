package com.binar.orderservice.service;

import com.binar.orderservice.dto.CinemaHallRequest;
import com.binar.orderservice.dto.CinemaHallResponse;

import java.util.List;

public interface CinemaHallService {

    CinemaHallResponse registerCinemaHall(CinemaHallRequest cinemaHallRequest);
    List<CinemaHallResponse> searchAllCinemaHall();
}
