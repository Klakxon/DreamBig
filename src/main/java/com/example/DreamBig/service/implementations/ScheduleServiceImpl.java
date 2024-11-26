package com.example.DreamBig.service.implementations;

import com.example.DreamBig.entity.Schedule;
import com.example.DreamBig.repository.ScheduleRepository;
import com.example.DreamBig.service.interfaces.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    @Cacheable(value = "schedules", key = "#id")
    public Schedule getScheduleById(Long id) {
        return scheduleRepository.findById(id).orElse(null);
    }

    @Override
    @Cacheable(value = "schedules", key = "'allSchedules'")
    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    @Override
    @CachePut(value = "schedules", key = "#schedule.id")
    public Schedule createSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    @Override
    @CachePut(value = "schedules", key = "#schedule.id")
    public Schedule updateSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    @Override
    @CacheEvict(value = "schedules", key = "#id")
    public void deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);
    }
}
