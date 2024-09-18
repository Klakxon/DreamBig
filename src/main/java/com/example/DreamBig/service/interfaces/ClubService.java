package com.example.DreamBig.service.interfaces;

import com.example.DreamBig.model.Club;
import com.example.DreamBig.model.Session;
import com.example.DreamBig.model.Trainer;

import java.util.List;

public interface ClubService {
    void addTrainer(Club club, Trainer trainer);
    void removeTrainer(Club club, Trainer trainer);
    void addSession(Club club, Session session);
    void removeSession(Club club, Session session);
    List<Session> getSessionsByDate(Club club, String date);
    boolean hasTrainer(Club club, Trainer trainer);
    void addTrainerIfAvailable(Club club, Trainer trainer, String dateTime);
}
