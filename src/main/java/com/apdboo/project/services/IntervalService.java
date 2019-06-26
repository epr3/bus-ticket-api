package com.apdboo.project.services;

import com.apdboo.project.models.Interval;
import com.apdboo.project.requests.IntervalRequest;

import java.util.List;

public interface IntervalService {
    Interval createInterval(IntervalRequest intervalRequest);
    Interval updateInterval(Long id, IntervalRequest intervalRequest);
    void deleteInterval(Long id);
    Interval getInterval(Long id);
    List<Interval> getIntervals();
}
