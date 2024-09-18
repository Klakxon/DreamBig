package com.example.DreamBig.service.implementations;

import com.example.DreamBig.model.FeedbackRequest;
import com.example.DreamBig.service.interfaces.FeedbackRequestService;
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
