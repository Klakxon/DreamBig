package com.example.DreamBig.service.implementations;

import com.example.DreamBig.entity.FeedbackRequest;
import com.example.DreamBig.repository.FeedbackRequestRepository;
import com.example.DreamBig.service.interfaces.FeedbackRequestService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
    @Cacheable(value = "feedbackRequests", key = "#id")
    public FeedbackRequest getFeedbackRequestById(Long id) {
        return feedbackRequestRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Feedback request not found with id " + id));
    }

    @Override
    @Cacheable(value = "feedbackRequests", key = "'allRequests'")
    public List<FeedbackRequest> getAllFeedbackRequests() {
        return feedbackRequestRepository.findAll();
    }

    @Override
    @CachePut(value = "feedbackRequests", key = "#feedbackRequest.id")
    public FeedbackRequest createFeedbackRequest(FeedbackRequest feedbackRequest) {
        return feedbackRequestRepository.save(feedbackRequest);
    }

    @Override
    @CachePut(value = "feedbackRequests", key = "#id")
    public FeedbackRequest updateFeedbackRequest(Long id, FeedbackRequest updatedRequest) {
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
    @CacheEvict(value = "feedbackRequests", key = "#id")
    public void deleteFeedbackRequest(Long id) {
        feedbackRequestRepository.deleteById(id);
    }
}