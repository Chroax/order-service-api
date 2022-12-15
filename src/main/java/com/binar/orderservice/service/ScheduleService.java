package com.binar.orderservice.service;

import com.binar.orderservice.dto.ScheduleRequest;
import com.binar.orderservice.dto.ScheduleResponse;

import java.util.List;

public interface ScheduleService
{
    ScheduleResponse addSchedule(ScheduleRequest scheduleRequest);
    List<ScheduleResponse> showFilmSchedulesByFilmName(String filmName);
}
