package com.example.DreamBig.service.interfaces;

import com.example.DreamBig.model.FeedbackRequest;

public interface FeedbackRequestService {
    boolean isProcessed(FeedbackRequest feedbackRequest);
    void markAsProcessed(FeedbackRequest feedbackRequest);
}
