package com.binar.orderservice.service.impl;

import com.binar.orderservice.dto.ScheduleRequest;
import com.binar.orderservice.dto.ScheduleResponse;
import com.binar.orderservice.model.CinemaHall;
import com.binar.orderservice.model.Schedules;
import com.binar.orderservice.repository.CinemaHallRepository;
import com.binar.orderservice.repository.SchedulesRepository;
import com.binar.orderservice.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SchedulesServiceImpl implements ScheduleService {
    @Autowired
    SchedulesRepository schedulesRepository;
    @Autowired
    CinemaHallRepository cinemaHallRepository;

    @Override
    public ScheduleResponse addSchedule(ScheduleRequest scheduleRequest) {
        try {
            Optional<CinemaHall> cinemaHall = cinemaHallRepository.findById(scheduleRequest.getCinemaHallId());

            if(cinemaHall.isPresent())
            {
                Schedules schedules = Schedules.builder()
                        .startTime(scheduleRequest.getStartTime())
                        .endTime(scheduleRequest.getEndTime())
                        .price(scheduleRequest.getPrice())
                        .date(scheduleRequest.getDate())
                        .cinemaHall(cinemaHall.get())
                        .filmsName(scheduleRequest.getFilmName())
                        .build();
                schedulesRepository.save(schedules);

                return ScheduleResponse.builder()
                        .scheduleId(schedules.getScheduleId())
                        .startTime(schedules.getStartTime())
                        .endTime(schedules.getEndTime())
                        .price(schedules.getPrice())
                        .date(schedules.getDate())
                        .cinemaHallId(schedules.getCinemaHall().getCinemaHallId())
                        .filmName(schedules.getFilmsName())
                        .build();
            }
            else
            {
                return ScheduleResponse.builder()
                        .message("Cinema hall id or film id not exist")
                        .build();
            }
        }catch (Exception ignore){
            return ScheduleResponse.builder()
                    .message("Create schedules failed")
                    .build();
        }
    }

    @Override
    public List<ScheduleResponse> showFilmSchedulesByFilmName(String filmName) {
        List<Schedules> allSchedule = schedulesRepository.findAllFilmScheduleByName(filmName);
        return toListScheduleResponse(allSchedule);
    }

    private List<ScheduleResponse> toListScheduleResponse(List<Schedules> allSchedule) {
        List<ScheduleResponse> allScheduleResponse = new ArrayList<>();
        for (Schedules schedules : allSchedule) {
            ScheduleResponse scheduleResponse = ScheduleResponse.builder()
                    .scheduleId(schedules.getScheduleId())
                    .startTime(schedules.getStartTime())
                    .endTime(schedules.getEndTime())
                    .price(schedules.getPrice())
                    .date(schedules.getDate())
                    .cinemaHallId(schedules.getCinemaHall().getCinemaHallId())
                    .filmName(schedules.getFilmsName())
                    .build();
            allScheduleResponse.add(scheduleResponse);
        }
        return allScheduleResponse;
    }
}
