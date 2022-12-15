package com.binar.orderservice.service;

import com.binar.orderservice.dto.BookingRequest;
import com.binar.orderservice.dto.BookingResponse;

import java.util.List;
import java.util.UUID;

public interface BookingService {

    BookingResponse createBooking(BookingRequest bookingRequest);

    List<BookingResponse> searchHistory(String email);
}
