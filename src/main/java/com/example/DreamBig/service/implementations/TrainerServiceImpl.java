package com.example.DreamBig.service.implementations;

import com.example.DreamBig.entity.TrainerEntity;
import com.example.DreamBig.repository.TrainerRepository;
import com.example.DreamBig.service.interfaces.SessionService;
import com.example.DreamBig.service.interfaces.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerServiceImpl implements TrainerService {

    @Autowired
    private TrainerRepository trainerRepository;

    private SessionService sessionService;

    @Override
    public TrainerEntity createTrainer(TrainerEntity trainer) {
        return trainerRepository.save(trainer);
    }

    @Override
    public TrainerEntity getTrainerById(Long id) {
        return trainerRepository.findById(id).orElse(null);
    }

    @Override
    public List<TrainerEntity> getAllTrainers() {
        return trainerRepository.findAll();
    }

    @Override
    public TrainerEntity updateTrainer(TrainerEntity trainer) {
        return trainerRepository.save(trainer);
    }

    @Override
    public void deleteTrainer(Long id) {
        trainerRepository.deleteById(id);
    }

    @Override
    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }
}
