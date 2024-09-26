package com.example.DreamBig.service.interfaces;

import com.example.DreamBig.entity.TrainerEntity;
import java.util.List;

public interface TrainerService {
    TrainerEntity createTrainer(TrainerEntity trainer);
    TrainerEntity getTrainerById(Long id);
    List<TrainerEntity> getAllTrainers();
    TrainerEntity updateTrainer(TrainerEntity trainer);
    void deleteTrainer(Long id);
    void setSessionService(SessionService sessionService);
}
