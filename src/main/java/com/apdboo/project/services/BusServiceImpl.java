package com.apdboo.project.services;

import com.apdboo.project.exceptions.BusNotFoundException;
import com.apdboo.project.models.Amenity;
import com.apdboo.project.models.Bus;
import com.apdboo.project.models.Driver;
import com.apdboo.project.repositories.AmenityRepository;
import com.apdboo.project.repositories.BusRepository;
import com.apdboo.project.repositories.DriverRepository;
import com.apdboo.project.requests.BusRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusServiceImpl implements BusService {
    @Autowired
    private AmenityRepository amenityRepository;

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Override
    public Bus createBus(BusRequest busRequest) {
        List<Amenity> amenities = this.amenityRepository.getAmenitiesByIds(busRequest.getAmenities());
        Driver driver = this.driverRepository.save(Driver.builder().name(busRequest.getName()).surname(busRequest.getSurname()).build());
        return this.busRepository.save(
                Bus.builder()
                        .plateNo(busRequest.getPlateNo())
                        .busMake(busRequest.getBusModel())
                        .busModel(busRequest.getBusModel())
                        .driver(driver)
                        .amenities(amenities)
                        .build());
    }

    @Override
    public Bus updateBus(Long id, BusRequest busRequest) {
        List<Amenity> amenities = this.amenityRepository.getAmenitiesByIds(busRequest.getAmenities());
        Bus bus = this.busRepository.findById(id).orElseThrow(BusNotFoundException::new);
        bus.setBusMake(busRequest.getBusMake());
        bus.setBusModel(busRequest.getBusModel());
        bus.setPlateNo(busRequest.getPlateNo());
        bus.setAmenities(amenities);
        this.busRepository.save(bus);
        return bus;
    }

    @Override
    public void deleteBus(Long id) {
        Bus bus = this.busRepository.findById(id).orElseThrow(BusNotFoundException::new);
        this.busRepository.delete(bus);
    }

    @Override
    public Bus getBus(Long id) {
        return this.busRepository.findById(id).orElseThrow(BusNotFoundException::new);
    }

    @Override
    public List<Bus> getBuses() {
        return this.busRepository.findAll();
    }
}
