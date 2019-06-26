package com.apdboo.project.services;

import com.apdboo.project.models.Passenger;
import com.apdboo.project.requests.PassengerRequest;

import java.util.List;

public interface PassengerService {
    Passenger createPassenger(PassengerRequest passengerRequest);
    Passenger updatePassenger(Long id, PassengerRequest passengerRequest);
    void deletePassenger(Long id);
    Passenger getPassenger(Long id);
    List<Passenger> getPassengers();
}
