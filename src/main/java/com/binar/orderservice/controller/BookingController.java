package com.binar.orderservice.controller;

import com.binar.orderservice.dto.*;
import com.binar.orderservice.service.BookingDetailService;
import com.binar.orderservice.service.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/booking", produces = {"application/json"})
public class BookingController {

    private static final Logger log = LoggerFactory.getLogger(BookingController.class);

    @Autowired
    BookingService bookingService;

    @Autowired
    BookingDetailService bookingDetailService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MessageModel> createBooking(@RequestBody BookingRequest bookingRequest)
    {
        MessageModel messageModel = new MessageModel();

        BookingResponse bookingResponse = bookingService.createBooking(bookingRequest);

        if(bookingResponse.getMessage() != null)
        {
            messageModel.setStatus(HttpStatus.CONFLICT.value());
            messageModel.setMessage(bookingResponse.getMessage());
            log.error("Failed create new booking, error : {}", bookingResponse.getMessage());
        }
        else
        {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("Create new booking");
            messageModel.setData(bookingResponse);
            log.info("Success create new booking with id {}", bookingResponse.getBookingId());
        }

        return ResponseEntity.ok().body(messageModel);
    }

    @PostMapping("/add/details")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MessageModel> createBookingDetails(@RequestBody BookingDetailRequest bookingDetails)
    {
        MessageModel messageModel = new MessageModel();

        BookingDetailResponse bookingDetailResponse = bookingDetailService.createBookingDetail(bookingDetails);

        if(bookingDetailResponse.getMessage() != null)
        {
            messageModel.setStatus(HttpStatus.CONFLICT.value());
            messageModel.setMessage("Failed to create booking detail");
            log.error("Failed create new booking detail, error : {}", bookingDetailResponse.getMessage());
        }
        else
        {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("Create new booking detail");
            messageModel.setData(bookingDetailResponse);
            log.info("Success create new booking detail");
        }

        return ResponseEntity.ok().body(messageModel);
    }

    @GetMapping("/get-all/{email}")
    public ResponseEntity<MessageModel> getBookingHistory(@PathVariable String email)
    {
        MessageModel messageModel = new MessageModel();
        try {
            List<BookingResponse> bookingResponses = bookingService.searchHistory(email);
            messageModel.setMessage("Success get all booking history with email : " + email);
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setData(bookingResponses);
            log.info("Success get all booking history with email {}", email);
        }catch (Exception exception)
        {
            messageModel.setMessage("Failed get all booking history with email : " + email);
            messageModel.setStatus(HttpStatus.BAD_GATEWAY.value());
            log.error("Failed get all booking history with email {} , error : {}", email, exception.getMessage());
        }
        return ResponseEntity.ok().body(messageModel);
    }
}
