package com.apdboo.project.services;

import com.apdboo.project.models.City;
import com.apdboo.project.requests.CityRequest;

import java.util.List;

public interface CityService {
    City createCity(CityRequest cityRequest);
    City updateCity(Long id, CityRequest cityRequest);
    void deleteCity(Long id);
    City getCity(Long id);
    List<City> getCities();
}
