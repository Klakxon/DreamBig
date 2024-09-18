package com.example.dreambig.service.interfaces;

import com.example.dreambig.model.Session;
import com.example.dreambig.model.User;

public interface SessionService {
    void addParticipant(Session session, User user);
    void removeParticipant(Session session, User user);
    boolean isFullyBooked(Session session);
    int getMaxParticipants(Session session);
    boolean isParticipant(Session session, User user);
}
