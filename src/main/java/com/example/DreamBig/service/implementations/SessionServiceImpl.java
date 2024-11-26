package com.example.DreamBig.service.implementations;

import com.example.DreamBig.entity.Session;
import com.example.DreamBig.repository.SessionRepository;
import com.example.DreamBig.service.interfaces.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    @Override
    @Cacheable(value = "sessions", key = "#id")
    public Session getSessionById(Long id) {
        return sessionRepository.findById(id).orElse(null);
    }

    @Override
    @Cacheable(value = "sessions", key = "'allSessions'")
    public List<Session> getAllSessions() {
        return sessionRepository.findAll();
    }

    @Override
    @CachePut(value = "sessions", key = "#session.id")
    public Session createSession(Session session) {
        return sessionRepository.save(session);
    }

    @Override
    @CachePut(value = "sessions", key = "#session.id")
    public Session updateSession(Session session) {
        return sessionRepository.save(session);
    }

    @Override
    @CacheEvict(value = "sessions", key = "#id")
    public void deleteSession(Long id) {
        sessionRepository.deleteById(id);
    }
}
