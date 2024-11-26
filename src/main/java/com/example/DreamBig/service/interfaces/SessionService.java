package com.example.DreamBig.service.interfaces;

import com.example.DreamBig.entity.Session;
import java.util.List;

public interface SessionService {
    Session createSession(Session session);
    Session getSessionById(Long id);
    List<Session> getAllSessions();
    Session updateSession(Session session);
    void deleteSession(Long id);
}
