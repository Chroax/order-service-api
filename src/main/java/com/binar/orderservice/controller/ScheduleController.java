package com.binar.orderservice.controller;

import com.binar.orderservice.dto.MessageModel;
import com.binar.orderservice.dto.ScheduleRequest;
import com.binar.orderservice.dto.ScheduleResponse;
import com.binar.orderservice.service.ScheduleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/schedule", produces = {"application/json"})
public class ScheduleController
{
    private static final Logger log = LoggerFactory.getLogger(ScheduleController.class);

    @Autowired
    ScheduleService scheduleService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MessageModel> addSchedule(@RequestBody ScheduleRequest scheduleRequest) {

        MessageModel messageModel = new MessageModel();

        ScheduleResponse scheduleResponse = scheduleService.addSchedule(scheduleRequest);

        if(scheduleResponse.getMessage() != null)
        {
            messageModel.setStatus(HttpStatus.CONFLICT.value());
            messageModel.setMessage(scheduleResponse.getMessage());
            log.error("Failed create new schedule, error : {} ", scheduleResponse.getMessage());
        }
        else
        {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("Register new schedule");
            messageModel.setData(scheduleResponse);
            log.info("Success create new schedule with id {} ", scheduleResponse.getScheduleId());
        }

        return ResponseEntity.ok().body(messageModel);
    }

    @GetMapping("/film/name/{filmName}")
    public ResponseEntity<MessageModel> getAllFilmSchedule(@PathVariable String filmName){
        MessageModel messageModel = new MessageModel();
        try {
            List<ScheduleResponse> schedulesGet = scheduleService.showFilmSchedulesByFilmName(filmName);
            messageModel.setMessage("Success get all film schedules by name");
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setData(schedulesGet);
            log.info("Success get all film schedules by name {}", filmName);
        }catch (Exception exception)
        {
            messageModel.setMessage("Failed get all film schedules by name");
            messageModel.setStatus(HttpStatus.BAD_GATEWAY.value());
            log.error("Failed get all film schedules by name {} , error : {} ", filmName, exception.getMessage());
        }
        return ResponseEntity.ok().body(messageModel);
    }
}
