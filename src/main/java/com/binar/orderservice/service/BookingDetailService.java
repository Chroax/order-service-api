package com.binar.orderservice.service;

import com.binar.orderservice.dto.BookingDetailRequest;
import com.binar.orderservice.dto.BookingDetailResponse;

public interface BookingDetailService {

    BookingDetailResponse createBookingDetail(BookingDetailRequest bookingDetails);
}
