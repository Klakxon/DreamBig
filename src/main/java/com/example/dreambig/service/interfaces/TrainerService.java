package com.example.dreambig.service.interfaces;

import com.example.dreambig.model.Trainer;
import com.example.dreambig.model.User;

public interface TrainerService {
    boolean isAvailable(Trainer trainer, String dateTime);
    void bookSession(Trainer trainer, User user, String dateTime, String sessionType);
    void cancelSession(Trainer trainer, String dateTime);
    boolean hasSessionAt(Trainer trainer, String dateTime);
}
