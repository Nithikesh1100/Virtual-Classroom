package com.classroom.virtualClassroom.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.classroom.virtualClassroom.Requests.AuthRequest;
import com.classroom.virtualClassroom.Requests.RegisterRequest;
import com.classroom.virtualClassroom.Response.AuthResponse;
import com.classroom.virtualClassroom.model.User;
import com.classroom.virtualClassroom.repository.UserRepository;




import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final UserRepository repository;
	private final PasswordEncoder passwordEncoder;
	private final JwtTokenProvider jwtTokenProvider;
	private final AuthenticationManager authenticationManager;

	public AuthResponse register(RegisterRequest request) {
		var user = User.builder()
				.username(request.getUsername())
				.email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword()))
				.role(request.getRole()).build();
		repository.save(user);
		var jwtToken = jwtTokenProvider.generateToken(user);

		return AuthResponse.builder().token(jwtToken)

				.build();
	}

	public AuthResponse authenticate(AuthRequest request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		var user = repository.findByEmail(request.getEmail()).orElseThrow();
		var jwtToken = jwtTokenProvider.generateToken(user);

		return AuthResponse.builder().token(jwtToken)

				.build();
	}

}
