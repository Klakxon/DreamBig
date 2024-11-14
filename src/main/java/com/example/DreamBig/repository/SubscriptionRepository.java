package com.example.DreamBig.repository;

import com.example.DreamBig.entity.SubscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM SubscriptionEntity s WHERE s.type = 'Temporary' AND s.isActive = false")
    int deleteInactiveTemporarySubscriptions();
}