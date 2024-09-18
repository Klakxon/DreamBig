package com.example.dreambig.service.interfaces;

import com.example.dreambig.model.Club;
import com.example.dreambig.model.Session;
import com.example.dreambig.model.Trainer;

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
