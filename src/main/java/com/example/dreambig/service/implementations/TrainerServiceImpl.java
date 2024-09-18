package com.example.dreambig.service.implementations;

import com.example.dreambig.model.Session;
import com.example.dreambig.model.Trainer;
import com.example.dreambig.model.User;
import com.example.dreambig.service.interfaces.SessionService;
import com.example.dreambig.service.interfaces.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerServiceImpl implements TrainerService {
    private final SessionService sessionService;

    @Autowired
    public TrainerServiceImpl(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    public boolean isAvailable(Trainer trainer, String dateTime) {
        List<Session> schedule = trainer.getSchedule();
        if (schedule == null || schedule.isEmpty()) {
            return true; // Тренер вільний
        }
        for (Session session : schedule) {
            if (session.getDateTime().equals(dateTime)) {
                return false; // Тренер зайнятий у цей час
            }
        }
        return true; // Тренер вільний
    }

    public void bookSession(Trainer trainer, User user, String dateTime, String sessionType) {
        if (!isAvailable(trainer, dateTime)) {
            throw new IllegalStateException("Trainer is not available at this time");
        }
        Session newSession = new Session(null, sessionType, dateTime, trainer);
        sessionService.addParticipant(newSession, user);
        trainer.getSchedule().add(newSession);
    }

    public void cancelSession(Trainer trainer, String dateTime) {
        List<Session> schedule = trainer.getSchedule();
        if (schedule != null && !schedule.isEmpty()) {
            schedule.removeIf(session -> session.getDateTime().equals(dateTime));
        }
    }

    public boolean hasSessionAt(Trainer trainer, String dateTime) {
        List<Session> schedule = trainer.getSchedule();
        if (schedule == null || schedule.isEmpty()) {
            return false;
        }
        for (Session session : schedule) {
            if (session.getDateTime().equals(dateTime)) {
                return true; // У тренера є сесія у вказаний час
            }
        }
        return false;
    }
}
