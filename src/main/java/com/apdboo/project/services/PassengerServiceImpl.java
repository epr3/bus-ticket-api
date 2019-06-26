package com.apdboo.project.services;

import com.apdboo.project.models.Passenger;
import com.apdboo.project.models.User;
import com.apdboo.project.repositories.PassengerRepository;
import com.apdboo.project.repositories.UserRepository;
import com.apdboo.project.requests.PassengerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerServiceImpl implements PassengerService {
    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Passenger createPassenger(PassengerRequest passengerRequest) {
        User user = this.userRepository.findByEmail(passengerRequest.getEmail())
                .orElseThrow(
                        () -> new UsernameNotFoundException("Email " + passengerRequest.getEmail() + " not found."));
        return this.passengerRepository.save(
                Passenger.builder()
                        .name(passengerRequest.getName())
                        .surname(passengerRequest
                                .getSurname())
                        .telephone(passengerRequest.getTelephone())
                        .user(user)
                        .build());
    }

    @Override
    public Passenger updatePassenger(Long id, PassengerRequest passengerRequest) {
        return null;
    }

    @Override
    public void deletePassenger(Long id) {

    }

    @Override
    public Passenger getPassenger(Long id) {
        return null;
    }

    @Override
    public List<Passenger> getPassengers() {
        return null;
    }
}
