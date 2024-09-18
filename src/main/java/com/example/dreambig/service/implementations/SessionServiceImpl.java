package com.example.dreambig.service.implementations;

import com.example.dreambig.model.Session;
import com.example.dreambig.model.User;
import com.example.dreambig.service.interfaces.SessionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionServiceImpl implements SessionService {
    public void addParticipant(Session session, User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }

        List<User> participants = session.getParticipants();
        if (participants.size() >= getMaxParticipants(session)) {
            throw new IllegalStateException("Session is fully booked");
        }
        participants.add(user);
    }

    public void removeParticipant(Session session, User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        session.getParticipants().remove(user);
    }

    public boolean isFullyBooked(Session session) {
        return session.getParticipants().size() >= getMaxParticipants(session);
    }

    public int getMaxParticipants(Session session) {
        return "Group".equalsIgnoreCase(session.getSessionType()) ? 10 : 1; // Групові сесії до 10 людей, індивідуальні - 1
    }

    public boolean isParticipant(Session session, User user) {
        return session.getParticipants().contains(user);
    }
}
