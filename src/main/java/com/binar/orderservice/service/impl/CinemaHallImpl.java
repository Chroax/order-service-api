package com.binar.orderservice.service.impl;

import com.binar.orderservice.dto.CinemaHallRequest;
import com.binar.orderservice.dto.CinemaHallResponse;
import com.binar.orderservice.model.CinemaHall;
import com.binar.orderservice.repository.CinemaHallRepository;
import com.binar.orderservice.service.CinemaHallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CinemaHallImpl implements CinemaHallService {

    @Autowired
    CinemaHallRepository cinemaHallRepository;

    @Override
    public CinemaHallResponse registerCinemaHall(CinemaHallRequest cinemaHallRequest) {
        CinemaHall cinemaHall = cinemaHallRequest.toCinemaHall();

        try {
            cinemaHallRepository.save(cinemaHall);
            return CinemaHallResponse.builder()
                    .cinemaHallId(cinemaHall.getCinemaHallId())
                    .cinemaHallName(cinemaHall.getCinemaHallName())
                    .build();
        }
        catch(Exception exception)
        {
            return CinemaHallResponse.builder()
                    .message("Cinema hall already exist")
                    .build();
        }
    }

    @Override
    public List<CinemaHallResponse> searchAllCinemaHall() {
        List<CinemaHall> allCinemaHall = cinemaHallRepository.findAll();
        List<CinemaHallResponse> allCinemaHallResponse = new ArrayList<>();
        for (CinemaHall cinemaHall: allCinemaHall) {
            CinemaHallResponse cinemaHallResponse = CinemaHallResponse.builder()
                    .cinemaHallId(cinemaHall.getCinemaHallId())
                    .cinemaHallName(cinemaHall.getCinemaHallName())
                    .build();
            allCinemaHallResponse.add(cinemaHallResponse);
        }
        return allCinemaHallResponse;
    }
}
