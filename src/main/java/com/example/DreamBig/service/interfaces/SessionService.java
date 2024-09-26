package com.example.DreamBig.service.interfaces;

import com.example.DreamBig.entity.SessionEntity;
import java.util.List;

public interface SessionService {
    SessionEntity createSession(SessionEntity session);
    SessionEntity getSessionById(Long id);
    List<SessionEntity> getAllSessions();
    SessionEntity updateSession(SessionEntity session);
    void deleteSession(Long id);
}
