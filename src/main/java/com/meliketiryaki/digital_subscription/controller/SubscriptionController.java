package com.meliketiryaki.digital_subscription.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meliketiryaki.digital_subscription.dto.SubscriptionDto;
import com.meliketiryaki.digital_subscription.mapper.DtoMapper;
import com.meliketiryaki.digital_subscription.model.Subscription;
import com.meliketiryaki.digital_subscription.service.SubscriptionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

	private final SubscriptionService subscriptionService;
	
	@PostMapping(path = "/add")
	public ResponseEntity<SubscriptionDto> addSubscription(
	        @AuthenticationPrincipal UserDetails userDetails,
	        @RequestBody Subscription subscription) {

	    Subscription saved = subscriptionService.addSubscription(userDetails.getUsername(), subscription);

	    SubscriptionDto response = DtoMapper.toSubscriptionDto(saved);
	    return ResponseEntity.ok(response);
	}
    
    @GetMapping(path = "/list-subscriptions")
    public ResponseEntity<List<Subscription>> getSubscriptions(
    		@AuthenticationPrincipal UserDetails userDetails) {
    	List<Subscription> subscriptions = subscriptionService.getSubscriptions(userDetails.getUsername());
    	return ResponseEntity.ok(subscriptions);
    }
    
    @DeleteMapping("/delete-subscription/{id}")
    public ResponseEntity<Void> deleteSubscriptions(@PathVariable Long id) {
    	subscriptionService.deleteSubscription(id);
    	return ResponseEntity.noContent().build();
    }
    
	
	
}
