package com.binar.orderservice.repository;

import com.binar.orderservice.model.Seats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seats, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM seats s where s.cinema_hall_id = :cinemaHallId")
    List<Seats> findAllSeatByCinemaHall(@Param("cinemaHallId") Integer cinemaHallId);
}
