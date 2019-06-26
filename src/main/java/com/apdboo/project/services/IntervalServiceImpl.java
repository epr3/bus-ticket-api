package com.apdboo.project.services;

import com.apdboo.project.exceptions.IntervalNotFoundException;
import com.apdboo.project.models.Interval;
import com.apdboo.project.repositories.IntervalRepository;
import com.apdboo.project.requests.IntervalRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntervalServiceImpl implements IntervalService {
    @Autowired
    private IntervalRepository intervalRepository;

    @Override
    public Interval createInterval(IntervalRequest intervalRequest) {
        return this.intervalRepository.save(
                Interval.builder()
                        .intervalStart(intervalRequest.getIntervalStart())
                        .intervalEnd(intervalRequest.getIntervalEnd())
                        .build());
    }

    @Override
    public Interval updateInterval(Long id, IntervalRequest intervalRequest) {
        Interval interval = this.intervalRepository.findById(id).orElseThrow(IntervalNotFoundException::new);
        interval.setIntervalStart(intervalRequest.getIntervalStart());
        interval.setIntervalEnd(intervalRequest.getIntervalEnd());
        this.intervalRepository.save(interval);
        return interval;
    }

    @Override
    public void deleteInterval(Long id) {
        Interval interval = this.intervalRepository.findById(id).orElseThrow(IntervalNotFoundException::new);
        this.intervalRepository.delete(interval);
    }

    @Override
    public Interval getInterval(Long id) {
        return this.intervalRepository.findById(id).orElseThrow(IntervalNotFoundException::new);
    }

    @Override
    public List<Interval> getIntervals() {
        return this.intervalRepository.findAll();
    }
}
