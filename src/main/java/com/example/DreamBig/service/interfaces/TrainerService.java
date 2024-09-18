package com.example.DreamBig.service.interfaces;

import com.example.DreamBig.model.Trainer;
import com.example.DreamBig.model.User;

public interface TrainerService {
    boolean isAvailable(Trainer trainer, String dateTime);
    void bookSession(Trainer trainer, User user, String dateTime, String sessionType);
    void cancelSession(Trainer trainer, String dateTime);
    boolean hasSessionAt(Trainer trainer, String dateTime);
}
