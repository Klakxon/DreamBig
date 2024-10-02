package com.example.DreamBig.service.implementations;

import com.example.DreamBig.entity.SessionEntity;
import com.example.DreamBig.repository.SessionRepository;
import com.example.DreamBig.service.interfaces.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    @Override
    public SessionEntity createSession(SessionEntity session) {
        return sessionRepository.save(session);
    }

    @Override
    public SessionEntity getSessionById(Long id) {
        return sessionRepository.findById(id).orElse(null);
    }

    @Override
    public List<SessionEntity> getAllSessions() {
        return sessionRepository.findAll();
    }

    @Override
    public SessionEntity updateSession(SessionEntity session) {
        return sessionRepository.save(session);
    }

    @Override
    public void deleteSession(Long id) {
        sessionRepository.deleteById(id);
    }
}
