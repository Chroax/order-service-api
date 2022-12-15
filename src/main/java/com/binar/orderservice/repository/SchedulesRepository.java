package com.binar.orderservice.repository;

import com.binar.orderservice.model.Schedules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface SchedulesRepository extends JpaRepository<Schedules, UUID>
{
    @Query(nativeQuery = true, value = "SELECT * FROM schedules s where s.film_name LIKE :filmName")
    List<Schedules>findAllFilmScheduleByName(@Param("filmName") String filmName);
}