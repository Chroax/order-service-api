package com.binar.orderservice.repository;

import com.binar.orderservice.model.BookingDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface BookingDetailsRepository extends JpaRepository<BookingDetails, UUID> {

    @Query(nativeQuery = true, value = "SELECT * FROM booking_details b where b.booking_id = :bookingId")
    List<BookingDetails> findAllBookingDetailById(@Param("bookingId") UUID bookingId);
}
