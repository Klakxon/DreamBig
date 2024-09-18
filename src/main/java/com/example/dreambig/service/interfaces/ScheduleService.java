package com.example.dreambig.service.interfaces;

import com.example.dreambig.model.Schedule;
import com.example.dreambig.model.Session;
import com.example.dreambig.model.Trainer;

import java.util.List;

public interface ScheduleService {
    void addSession(Schedule schedule, Session session);
    void removeSession(Schedule schedule, Session session);
    List<Session> getSessionsByTrainer(Schedule schedule, Trainer trainer);
    boolean hasSession(Schedule schedule, Session session);
    boolean isFullyBooked(Schedule schedule);
}
