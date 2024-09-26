package com.example.DreamBig.service.interfaces;

import com.example.DreamBig.entity.ScheduleEntity;
import java.util.List;

public interface ScheduleService {
    ScheduleEntity createSchedule(ScheduleEntity schedule);
    ScheduleEntity getScheduleById(Long id);
    List<ScheduleEntity> getAllSchedules();
    ScheduleEntity updateSchedule(ScheduleEntity schedule);
    void deleteSchedule(Long id);
}
