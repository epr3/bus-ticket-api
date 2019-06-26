package com.apdboo.project.services;

import com.apdboo.project.exceptions.CityNotFoundException;
import com.apdboo.project.exceptions.RouteNotFoundException;
import com.apdboo.project.models.City;
import com.apdboo.project.models.Route;
import com.apdboo.project.repositories.CityRepository;
import com.apdboo.project.repositories.RouteRepository;
import com.apdboo.project.requests.RouteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {
    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private CityRepository cityRepository;

    @Override
    public Route createRoute(RouteRequest routeRequest) {
         City startCity = this.cityRepository.findById(routeRequest.getStartCityId()).orElseThrow(CityNotFoundException::new);
        City endCity = this.cityRepository.findById(routeRequest.getEndCityId()).orElseThrow(CityNotFoundException::new);
        return this.routeRepository.save(
                Route.builder()
                        .price(routeRequest.getPrice())
                        .distance(routeRequest.getDistance())
                        .startCity(startCity)
                        .endCity(endCity)
                        .build());
    }

    @Override
    public Route updateRoute(Long id, RouteRequest routeRequest) {
        City startCity = this.cityRepository.findById(routeRequest.getStartCityId()).orElseThrow(CityNotFoundException::new);
        City endCity = this.cityRepository.findById(routeRequest.getEndCityId()).orElseThrow(CityNotFoundException::new);
        Route route = this.routeRepository.findById(id).orElseThrow(RouteNotFoundException::new);
        route.setPrice(routeRequest.getPrice());
        route.setDistance(routeRequest.getDistance());
        route.setStartCity(startCity);
        route.setEndCity(endCity);
        this.routeRepository.save(route);
        return route;
    }

    @Override
    public void deleteRoute(Long id) {
        Route route = this.routeRepository.findById(id).orElseThrow(RouteNotFoundException::new);
        this.routeRepository.delete(route);
    }

    @Override
    public Route getRoute(Long id) {
        return this.routeRepository.findById(id).orElseThrow(RouteNotFoundException::new);
    }

    @Override
    public List<Route> getRoutes() {
        return this.routeRepository.findAll();
    }
}
