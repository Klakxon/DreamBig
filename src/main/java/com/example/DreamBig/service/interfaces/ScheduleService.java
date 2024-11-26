package com.example.DreamBig.service.interfaces;

import com.example.DreamBig.entity.Schedule;
import java.util.List;

public interface ScheduleService {
    Schedule createSchedule(Schedule schedule);
    Schedule getScheduleById(Long id);
    List<Schedule> getAllSchedules();
    Schedule updateSchedule(Schedule schedule);
    void deleteSchedule(Long id);
}
