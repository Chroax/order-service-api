package com.binar.orderservice.repository;

import com.binar.orderservice.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {

    @Query(nativeQuery = true, value = "SELECT * FROM bookings b where b.email = :email")
    List<Booking> findAllBookingByEmail(@Param("email") String email);
}
