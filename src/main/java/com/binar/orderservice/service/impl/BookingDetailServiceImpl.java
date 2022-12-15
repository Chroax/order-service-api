package com.binar.orderservice.service.impl;

import com.binar.orderservice.dto.BookingDetailRequest;
import com.binar.orderservice.dto.BookingDetailResponse;
import com.binar.orderservice.model.Booking;
import com.binar.orderservice.model.BookingDetails;
import com.binar.orderservice.model.Seats;
import com.binar.orderservice.repository.BookingDetailsRepository;
import com.binar.orderservice.repository.BookingRepository;
import com.binar.orderservice.repository.SeatRepository;
import com.binar.orderservice.service.BookingDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookingDetailServiceImpl implements BookingDetailService {

    @Autowired
    BookingDetailsRepository bookingDetailsRepository;
    @Autowired
    SeatRepository seatRepository;
    @Autowired
    BookingRepository bookingRepository;

    @Override
    public BookingDetailResponse createBookingDetail(BookingDetailRequest bookingDetailRequest) {
        try {
            Optional<Booking> booking = bookingRepository.findById(bookingDetailRequest.getBookingId());
            Optional<Seats> seats = seatRepository.findById(bookingDetailRequest.getSeatId());

            if(booking.isPresent() && seats.isPresent())
            {
                BookingDetails bookingDetails = BookingDetails.builder()
                        .booking(booking.get())
                        .seats(seats.get())
                        .status(bookingDetailRequest.getStatus())
                        .build();

                bookingDetailsRepository.saveAndFlush(bookingDetails);
                return BookingDetailResponse.builder()
                        .bookingId(bookingDetails.getBooking().getBookingId())
                        .seatId(bookingDetails.getSeats().getSeatId())
                        .status(bookingDetails.getStatus())
                        .build();
            }
            else
            {
                return BookingDetailResponse.builder()
                        .message("Booking id or seat id not exist")
                        .build();
            }
        }catch (Exception ignore){
            return BookingDetailResponse.builder()
                    .message("Create booking details failed")
                    .build();
        }
    }
}
