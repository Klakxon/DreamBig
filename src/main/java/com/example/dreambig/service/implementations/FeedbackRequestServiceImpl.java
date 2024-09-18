package com.example.dreambig.service.implementations;

import com.example.dreambig.model.FeedbackRequest;
import com.example.dreambig.service.interfaces.FeedbackRequestService;
import org.springframework.stereotype.Service;

@Service
public class FeedbackRequestServiceImpl implements FeedbackRequestService {
    public boolean isProcessed(FeedbackRequest feedbackRequest) {
        return "Processed".equalsIgnoreCase(feedbackRequest.getStatus());
    }

    public void markAsProcessed(FeedbackRequest feedbackRequest) {
        feedbackRequest.setStatus("Processed");
    }
}
