package com.apdboo.project.repositories;

import com.apdboo.project.models.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {

    @Query("SELECT passenger FROM Passenger passenger WHERE passenger.user_id = :userId")
    Passenger getPassengerByUserId(@Param("userId") Long userId);
}
