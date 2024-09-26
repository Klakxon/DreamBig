package com.example.DreamBig.service.interfaces;

import com.example.DreamBig.entity.FeedbackRequestEntity;

import java.util.List;

public interface FeedbackRequestService {

    List<FeedbackRequestEntity> getAllFeedbackRequests();

    FeedbackRequestEntity getFeedbackRequestById(Long id);

    FeedbackRequestEntity createFeedbackRequest(FeedbackRequestEntity feedbackRequest);

    FeedbackRequestEntity updateFeedbackRequest(Long id, FeedbackRequestEntity updatedRequest);

    void deleteFeedbackRequest(Long id);
}
