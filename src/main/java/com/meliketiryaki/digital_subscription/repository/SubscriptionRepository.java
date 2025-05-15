package com.meliketiryaki.digital_subscription.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meliketiryaki.digital_subscription.model.Subscription;
import com.meliketiryaki.digital_subscription.model.User;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
List<Subscription> findByUser(User user);
}
