package com.example.DreamBig.service.implementations;

import com.example.DreamBig.model.Schedule;
import com.example.DreamBig.model.Session;
import com.example.DreamBig.model.Trainer;
import com.example.DreamBig.service.interfaces.ScheduleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    public void addSession(Schedule schedule, Session session) {
        if (session == null) {
            throw new IllegalArgumentException("Session cannot be null");
        }
        if (!session.getDateTime().contains(schedule.getDate())) {
            throw new IllegalArgumentException("Session date does not match the schedule date");
        }
        schedule.getSessions().add(session);
    }

    public void removeSession(Schedule schedule, Session session) {
        if (session == null) {
            throw new IllegalArgumentException("Session cannot be null");
        }
        schedule.getSessions().remove(session);
    }

    public List<Session> getSessionsByTrainer(Schedule schedule, Trainer trainer) {
        List<Session> trainerSessions = new ArrayList<>();
        for (Session session : schedule.getSessions()) {
            if (session.getTrainer().equals(trainer)) {
                trainerSessions.add(session);
            }
        }
        return trainerSessions;
    }

    public boolean hasSession(Schedule schedule, Session session) {
        return schedule.getSessions().contains(session);
    }

    public boolean isFullyBooked(Schedule schedule) {
        // You could add logic here to determine if all available session spots are booked
        for (Session session : schedule.getSessions()) {
            if (session.getParticipants().size() < 10) { // Example: a session can have up to 10 participants
                return false;
            }
        }
        return true;
    }
}
