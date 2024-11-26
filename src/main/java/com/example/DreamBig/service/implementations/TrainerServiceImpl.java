package com.example.DreamBig.service.implementations;

import com.example.DreamBig.entity.Trainer;
import com.example.DreamBig.repository.TrainerRepository;
import com.example.DreamBig.service.interfaces.SessionService;
import com.example.DreamBig.service.interfaces.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerServiceImpl implements TrainerService {

    @Autowired
    private TrainerRepository trainerRepository;

    private SessionService sessionService;

    @Override
    @Cacheable(value = "trainers", key = "#id")
    public Trainer getTrainerById(Long id) {
        return trainerRepository.findById(id).orElse(null);
    }

    @Override
    @Cacheable(value = "trainers", key = "'allTrainers'")
    public List<Trainer> getAllTrainers() {
        return trainerRepository.findAll();
    }

    @Override
    @CachePut(value = "trainers", key = "#trainer.id")
    public Trainer createTrainer(Trainer trainer) {
        return trainerRepository.save(trainer);
    }

    @Override
    @CachePut(value = "trainers", key = "#trainer.id")
    public Trainer updateTrainer(Trainer trainer) {
        return trainerRepository.save(trainer);
    }

    @Override
    @CacheEvict(value = "trainers", key = "#id")
    public void deleteTrainer(Long id) {
        trainerRepository.deleteById(id);
    }

    @Override
    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }
}
