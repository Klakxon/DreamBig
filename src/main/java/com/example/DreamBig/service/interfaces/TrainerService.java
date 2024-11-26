package com.example.DreamBig.service.interfaces;

import com.example.DreamBig.entity.Trainer;
import java.util.List;

public interface TrainerService {
    Trainer createTrainer(Trainer trainer);
    Trainer getTrainerById(Long id);
    List<Trainer> getAllTrainers();
    Trainer updateTrainer(Trainer trainer);
    void deleteTrainer(Long id);
    void setSessionService(SessionService sessionService);
}
