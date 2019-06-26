package com.apdboo.project.repositories;


import com.apdboo.project.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query("SELECT ticket FROM Ticket ticket WHERE ticket.passenger_id = :passengerId")
    List<Ticket> getTicketsByPassengerId(@Param("userId") Long passengerId);
}
