package com.binar.orderservice.service.impl;

import com.binar.orderservice.dto.BookingRequest;
import com.binar.orderservice.dto.BookingResponse;
import com.binar.orderservice.model.Booking;
import com.binar.orderservice.model.Schedules;
import com.binar.orderservice.repository.BookingRepository;
import com.binar.orderservice.repository.SchedulesRepository;
import com.binar.orderservice.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    SchedulesRepository schedulesRepository;

    @Override
    public BookingResponse createBooking(BookingRequest bookingRequest) {
        try {
            Optional<Schedules> schedules = schedulesRepository.findById(bookingRequest.getSchedulesId());

            if(schedules.isPresent())
            {
                Booking booking = Booking.builder()
                        .email(bookingRequest.getEmail())
                        .schedulesBook(schedules.get())
                        .totalSeat(bookingRequest.getTotalSeat())
                        .build();

                bookingRepository.saveAndFlush(booking);

                return BookingResponse.builder()
                        .bookingId(booking.getBookingId())
                        .createdAt(booking.getCreatedAt())
                        .email(booking.getEmail())
                        .schedulesId(booking.getSchedulesBook().getScheduleId())
                        .totalSeat(booking.getTotalSeat())
                        .build();
            }
            else
            {
                return BookingResponse.builder()
                        .message("Schedule id or user id not exist")
                        .build();
            }
        }catch (Exception ignore){
            return BookingResponse.builder()
                    .message("Create booking failed")
                    .build();
        }
    }

    @Override
    public List<BookingResponse> searchHistory(String email) {
        List<Booking> alLBooking = bookingRepository.findAllBookingByEmail(email);
        List<BookingResponse> allBookingResponse = new ArrayList<>();
        for (Booking booking : alLBooking) {
            BookingResponse bookingResponse = BookingResponse.builder()
                    .bookingId(booking.getBookingId())
                    .createdAt(booking.getCreatedAt())
                    .email(booking.getEmail())
                    .schedulesId(booking.getSchedulesBook().getScheduleId())
                    .totalSeat(booking.getTotalSeat())
                    .build();
            allBookingResponse.add(bookingResponse);
        }
        return allBookingResponse;
    }
}
