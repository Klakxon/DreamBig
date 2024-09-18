package com.example.DreamBig.service.interfaces;

import com.example.DreamBig.model.Session;
import com.example.DreamBig.model.User;

public interface SessionService {
    void addParticipant(Session session, User user);
    void removeParticipant(Session session, User user);
    boolean isFullyBooked(Session session);
    int getMaxParticipants(Session session);
    boolean isParticipant(Session session, User user);
}
