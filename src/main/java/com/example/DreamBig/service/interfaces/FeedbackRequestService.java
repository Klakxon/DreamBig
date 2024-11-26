package com.example.DreamBig.service.interfaces;

import com.example.DreamBig.entity.FeedbackRequest;

import java.util.List;

public interface FeedbackRequestService {

    List<FeedbackRequest> getAllFeedbackRequests();

    FeedbackRequest getFeedbackRequestById(Long id);

    FeedbackRequest createFeedbackRequest(FeedbackRequest feedbackRequest);

    FeedbackRequest updateFeedbackRequest(Long id, FeedbackRequest updatedRequest);

    void deleteFeedbackRequest(Long id);
}
