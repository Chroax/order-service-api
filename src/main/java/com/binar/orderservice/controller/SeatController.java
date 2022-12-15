package com.binar.orderservice.controller;

import com.binar.orderservice.dto.MessageModel;
import com.binar.orderservice.dto.SeatRequest;
import com.binar.orderservice.dto.SeatResponse;
import com.binar.orderservice.service.SeatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/seat", produces = {"application/json"})
public class SeatController {
    private static final Logger log = LoggerFactory.getLogger(SeatController.class);

    @Autowired
    SeatService seatService;

    @Operation(responses = {
            @ApiResponse(responseCode = "200", content = @Content(examples = {
                    @ExampleObject(name = "Create seat",
                            description = "Pastikan cinema hall id valid.",
                            value = """
                                    {
                                    responseCode : 200
                                      "responseMessage": "Register new seat",
                                      "data": [
                                        {
                                          "seat_id": 1,
                                          "seat_number": A01,
                                          "cinema_hall_id": "1"
                                        }
                                      ]
                                    }""")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE))})
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MessageModel> addSeat(@RequestBody SeatRequest seatRequest) {
        MessageModel messageModel = new MessageModel();

        SeatResponse seatResponse = seatService.addSeat(seatRequest);

        if(seatResponse.getMessage() != null)
        {
            messageModel.setStatus(HttpStatus.CONFLICT.value());
            messageModel.setMessage(seatResponse.getMessage());
            log.error("Failed create new seat with id {}, error : {}", seatResponse.getSeatId(), seatResponse.getMessage());
        }
        else
        {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("Register new seat");
            messageModel.setData(seatResponse);
            log.info("Success create new seat with id {}", seatResponse.getSeatId());
        }

        return ResponseEntity.ok().body(messageModel);
    }

    @Operation(responses = {
            @ApiResponse(responseCode = "200", content = @Content(examples = {
                    @ExampleObject(name = "Get all cinema hall seat",
                            description = "Pastikan cinema hall id valid.",
                            value = """
                                    {
                                    responseCode : 200
                                      "responseMessage": "Register new seat",
                                      "data": [
                                        {
                                          "seat_id": 1,
                                          "seat_number": A01,
                                          "cinema_hall_id": "1"
                                        },
                                        {
                                          "seat_id": 2,
                                          "seat_number": A02,
                                          "cinema_hall_id": "1"
                                        }
                                      ]
                                    }""")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE))})
    @GetMapping("/cinema-hall/{cinemaHallId}")
    public ResponseEntity<MessageModel> getAllSeatByCinemaHall(@PathVariable Integer cinemaHallId) {

        MessageModel messageModel = new MessageModel();
        try {
            List<SeatResponse> seatGet = seatService.showAllSeatByCinemaHall(cinemaHallId);
            messageModel.setMessage("Success get all seat by cinema hall");
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setData(seatGet);
            log.info("Success get all seat by cinema hall with id {}", cinemaHallId);
        }catch (Exception exception)
        {
            messageModel.setMessage("Failed get all seat by cinema hall");
            messageModel.setStatus(HttpStatus.BAD_GATEWAY.value());
            log.error("Failed get all seat by cinema hall with id {}", cinemaHallId);
        }
        return ResponseEntity.ok().body(messageModel);
    }
}
