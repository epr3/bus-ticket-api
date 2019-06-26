package com.apdboo.project.services;

import com.apdboo.project.models.Bus;
import com.apdboo.project.requests.BusRequest;

import java.util.List;

public interface BusService {
    Bus createBus(BusRequest busRequest);
    Bus updateBus(Long id, BusRequest busRequest);
    void deleteBus(Long id);
    Bus getBus(Long id);
    List<Bus> getBuses();
}
