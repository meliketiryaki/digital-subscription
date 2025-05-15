package com.meliketiryaki.digital_subscription.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.meliketiryaki.digital_subscription.model.Subscription;
import com.meliketiryaki.digital_subscription.model.User;
import com.meliketiryaki.digital_subscription.repository.SubscriptionRepository;
import com.meliketiryaki.digital_subscription.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

	private final SubscriptionRepository subscriptionRepository;
	private final UserRepository userRepository;
	

	public Subscription addSubscription(String username, Subscription subscription) {
		User user = userRepository.findByEmail(username)
				.orElseThrow(()-> new RuntimeException("Kullanıcı bulunamadı!"));
		subscription.setUser(user);
		return subscriptionRepository.save(subscription);
	}
	
	public List<Subscription> getSubscriptions(String username) {
		User user = userRepository.findByEmail(username)
				.orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı: " + username));
		return subscriptionRepository.findByUser(user);
	}
	
	public void deleteSubscription(Long id) {
		subscriptionRepository.deleteById(id);
	}
}
