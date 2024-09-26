package com.example.DreamBig.service.implementations;

import com.example.DreamBig.entity.FeedbackRequestEntity;
import com.example.DreamBig.repository.FeedbackRequestRepository;
import com.example.DreamBig.service.interfaces.FeedbackRequestService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackRequestServiceImpl implements FeedbackRequestService {

    private final FeedbackRequestRepository feedbackRequestRepository;

    @Autowired
    public FeedbackRequestServiceImpl(FeedbackRequestRepository feedbackRequestRepository) {
        this.feedbackRequestRepository = feedbackRequestRepository;
    }

    @Override
    public List<FeedbackRequestEntity> getAllFeedbackRequests() {
        return feedbackRequestRepository.findAll();
    }

    @Override
    public FeedbackRequestEntity getFeedbackRequestById(Long id) {
        return feedbackRequestRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Feedback request not found with id " + id));
    }

    @Override
    public FeedbackRequestEntity createFeedbackRequest(FeedbackRequestEntity feedbackRequest) {
        return feedbackRequestRepository.save(feedbackRequest);
    }

    @Override
    public FeedbackRequestEntity updateFeedbackRequest(Long id, FeedbackRequestEntity updatedRequest) {
        return feedbackRequestRepository.findById(id)
                .map(request -> {
                    request.setPhoneNumber(updatedRequest.getPhoneNumber());
                    request.setMessage(updatedRequest.getMessage());
                    request.setStatus(updatedRequest.getStatus());
                    return feedbackRequestRepository.save(request);
                })
                .orElseThrow(() -> new EntityNotFoundException("Feedback request not found with id " + id));
    }

    @Override
    public void deleteFeedbackRequest(Long id) {
        feedbackRequestRepository.deleteById(id);
    }
}