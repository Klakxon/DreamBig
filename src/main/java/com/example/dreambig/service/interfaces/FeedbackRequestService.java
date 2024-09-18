package com.example.dreambig.service.interfaces;

import com.example.dreambig.model.FeedbackRequest;

public interface FeedbackRequestService {
    boolean isProcessed(FeedbackRequest feedbackRequest);
    void markAsProcessed(FeedbackRequest feedbackRequest);
}
