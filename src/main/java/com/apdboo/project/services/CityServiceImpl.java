package com.apdboo.project.services;

import com.apdboo.project.exceptions.CityNotFoundException;
import com.apdboo.project.models.City;
import com.apdboo.project.repositories.CityRepository;
import com.apdboo.project.requests.CityRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityRepository cityRepository;

    @Override
    public City createCity(CityRequest cityRequest) {
        return this.cityRepository.save(City.builder().name(cityRequest.getName()).build());
    }

    @Override
    public City updateCity(Long id, CityRequest cityRequest) {
        City city = this.cityRepository.findById(id).orElseThrow(CityNotFoundException::new);
        city.setName(cityRequest.getName());
        this.cityRepository.save(city);
        return city;
    }

    @Override
    public void deleteCity(Long id) {
        City city = this.cityRepository.findById(id).orElseThrow(CityNotFoundException::new);
        this.cityRepository.delete(city);
    }

    @Override
    public City getCity(Long id) {
        return this.cityRepository.findById(id).orElseThrow(CityNotFoundException::new);
    }

    @Override
    public List<City> getCities() {
        return this.cityRepository.findAll();
    }
}
