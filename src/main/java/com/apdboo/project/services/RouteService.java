package com.apdboo.project.services;

import com.apdboo.project.models.Route;
import com.apdboo.project.requests.RouteRequest;

import java.util.List;

public interface RouteService {
    Route createRoute(RouteRequest routeRequest);
    Route updateRoute(Long id, RouteRequest routeRequest);
    void deleteRoute(Long id);
    Route getRoute(Long id);
    List<Route> getRoutes();
}
