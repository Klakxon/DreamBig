package com.example.DreamBig.service.implementations;

import com.example.DreamBig.entity.ScheduleEntity;
import com.example.DreamBig.repository.ScheduleRepository;
import com.example.DreamBig.service.interfaces.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public ScheduleEntity createSchedule(ScheduleEntity schedule) {
        return scheduleRepository.save(schedule);
    }

    @Override
    public ScheduleEntity getScheduleById(Long id) {
        return scheduleRepository.findById(id).orElse(null);
    }

    @Override
    public List<ScheduleEntity> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    @Override
    public ScheduleEntity updateSchedule(ScheduleEntity schedule) {
        return scheduleRepository.save(schedule);
    }

    @Override
    public void deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);
    }
}