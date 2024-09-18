package com.example.DreamBig.service.implementations;

import com.example.DreamBig.model.Club;
import com.example.DreamBig.model.Session;
import com.example.DreamBig.model.Trainer;
import com.example.DreamBig.service.interfaces.ClubService;
import com.example.DreamBig.service.interfaces.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClubServiceImpl implements ClubService {
    private TrainerService trainerService;

    @Autowired
    public void setTrainerService(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    public void addTrainer(Club club, Trainer trainer) {
        if (trainer == null) {
            throw new IllegalArgumentException("Trainer cannot be null");
        }
        club.getTrainers().add(trainer);
    }

    public void removeTrainer(Club club, Trainer trainer) {
        club.getTrainers().remove(trainer);
    }

    public void addSession(Club club, Session session) {
        if (session == null) {
            throw new IllegalArgumentException("Session cannot be null");
        }
        club.getSessions().add(session);
    }

    public void removeSession(Club club, Session session) {
        club.getSessions().remove(session);
    }

    public List<Session> getSessionsByDate(Club club, String date) {
        List<Session> sessionsByDate = new ArrayList<>();
        for (Session session : club.getSessions()) {
            if (session.getDateTime().contains(date)) {
                sessionsByDate.add(session);
            }
        }
        return sessionsByDate;
    }

    public boolean hasTrainer(Club club, Trainer trainer) {
        return club.getTrainers().contains(trainer);
    }

    public void addTrainerIfAvailable(Club club, Trainer trainer, String dateTime) {
        if (!trainerService.isAvailable(trainer, dateTime)) {
            throw new IllegalArgumentException("Trainer is not available at the specified time.");
        }
        addTrainer(club, trainer);
    }

}
