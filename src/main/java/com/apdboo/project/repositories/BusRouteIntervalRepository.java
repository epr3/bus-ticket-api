package com.apdboo.project.repositories;

import com.apdboo.project.models.BusRouteInteval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusRouteIntervalRepository extends JpaRepository<BusRouteInteval, Long> {
}
