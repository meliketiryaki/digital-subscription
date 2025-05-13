package com.meliketiryaki.digital_subscription.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meliketiryaki.digital_subscription.dto.AuthRequest;
import com.meliketiryaki.digital_subscription.dto.AuthResponse;
import com.meliketiryaki.digital_subscription.dto.RegisterRequest;
import com.meliketiryaki.digital_subscription.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;
	
	@PostMapping("/register")
	public AuthResponse register(@RequestBody RegisterRequest request) {
		return authService.register(request);
	}
	
	@PostMapping("/login")
	public AuthResponse login(@RequestBody AuthRequest request) {
		return authService.login(request);
	}
	
}
