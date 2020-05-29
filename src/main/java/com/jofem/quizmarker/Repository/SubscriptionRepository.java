package com.jofem.quizmarker.Repository;

import com.jofem.quizmarker.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
}
