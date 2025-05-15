package com.meliketiryaki.digital_subscription.service;

import com.meliketiryaki.digital_subscription.model.Role;
import com.meliketiryaki.digital_subscription.model.User;
import com.meliketiryaki.digital_subscription.repository.UserRepository;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
	
	private final AuthenticationManager authenticationManager;
	
	public AuthResponse register(RegisterRequest request) {
		User user = User.builder()
				.email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword()))
				.role(Role.USER)
				.build();
		
		userRepository.save(user);
		String token = jwtService.generateToken(user.getEmail());
		
		return new AuthResponse(token);
	}
	
	public AuthResponse login(AuthRequest request) {
		 authenticationManager.authenticate(
			        new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
			    );
			    
			    User user = userRepository.findByEmail(request.getEmail())
			            .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı!"));

			    String token = jwtService.generateToken(user.getEmail());
			    return new AuthResponse(token);
		
	}
}
