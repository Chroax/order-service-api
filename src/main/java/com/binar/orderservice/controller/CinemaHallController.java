package com.binar.orderservice.controller;

import com.binar.orderservice.dto.CinemaHallRequest;
import com.binar.orderservice.dto.CinemaHallResponse;
import com.binar.orderservice.dto.MessageModel;
import com.binar.orderservice.service.CinemaHallService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cinema-hall", produces = {"application/json"})
public class CinemaHallController {

    @Autowired
    CinemaHallService cinemaHallService;

    private static final Logger log = LoggerFactory.getLogger(CinemaHallController.class);


    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MessageModel> createCinemaHall(@RequestBody CinemaHallRequest cinemaHallRequest)
    {
        MessageModel messageModel = new MessageModel();

        CinemaHallResponse cinemaHallResponse = cinemaHallService.registerCinemaHall(cinemaHallRequest);

        if(cinemaHallResponse.getMessage() != null)
        {
            messageModel.setStatus(HttpStatus.CONFLICT.value());
            messageModel.setMessage(cinemaHallResponse.getMessage());
            log.error("Failed create new cinema hall, error : {}", cinemaHallResponse.getMessage());
        }
        else
        {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("Register new cinema hall");
            messageModel.setData(cinemaHallResponse);
            log.info("Success create new cinema hall with id {}", cinemaHallResponse.getCinemaHallId());
        }

        return ResponseEntity.ok().body(messageModel);
    }

    @GetMapping("/get-all")
    public ResponseEntity<MessageModel> getAllCinemaHall()
    {
        MessageModel messageModel = new MessageModel();
        try {
            List<CinemaHallResponse> cinemaHallGet = cinemaHallService.searchAllCinemaHall();
            messageModel.setMessage("Success get all cinema hall");
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setData(cinemaHallGet);
            log.info("Success get all cinema hall");
        }catch (Exception exception)
        {
            messageModel.setMessage("Failed get all cinema hall");
            messageModel.setStatus(HttpStatus.BAD_GATEWAY.value());
            log.error("Failed get all cinema hall, error : {}", exception.getMessage());
        }
        return ResponseEntity.ok().body(messageModel);
    }
}
