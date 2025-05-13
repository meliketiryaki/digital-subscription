package com.meliketiryaki.digital_subscription.service;

import com.meliketiryaki.digital_subscription.model.User;
import com.meliketiryaki.digital_subscription.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.meliketiryaki.digital_subscription.dto.AuthRequest;
import com.meliketiryaki.digital_subscription.dto.AuthResponse;
import com.meliketiryaki.digital_subscription.dto.RegisterRequest;
import com.meliketiryaki.digital_subscription.security.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	
	public AuthResponse register(RegisterRequest request) {
		User user = User.builder()
				.email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword()))
				.build();
		
		userRepository.save(user);
		String token = jwtService.generateToken(user.getEmail());
		
		return new AuthResponse(token);
	}
	
	public AuthResponse login(AuthRequest request) {
		User user = userRepository.findByEmail(request.getEmail())
				.orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı!"));
		
		if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			throw new RuntimeException("Şifre hatalı");
			
		}
		
		String token = jwtService.generateToken(user.getEmail());
		return new AuthResponse(token);
		
	}
}
