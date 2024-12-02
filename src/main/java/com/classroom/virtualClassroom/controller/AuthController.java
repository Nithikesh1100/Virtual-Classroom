package com.classroom.virtualClassroom.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.classroom.virtualClassroom.Requests.AuthRequest;
import com.classroom.virtualClassroom.Requests.RegisterRequest;
import com.classroom.virtualClassroom.Response.AuthResponse;
import com.classroom.virtualClassroom.model.User;
import com.classroom.virtualClassroom.repository.UserRepository;
import com.classroom.virtualClassroom.service.AuthService;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
//@CrossOrigin(origins = "http://localhost:3000/")
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService service;
	private final UserRepository userRepository;
	

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
		if (userRepository.existsByEmail(request.getEmail())) {
			return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(service.register(request));
	}

	@PostMapping("/login")
	public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest request) {
		return ResponseEntity.ok(service.authenticate(request));
	}

	@GetMapping("/login")
	public String login() {
		return "Login Woking";
	}

//	@GetMapping("/logout")
//	public String logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
//		if (authentication != null) {
//			new SecurityContextLogoutHandler().logout(request, response, authentication);
//		}
//		return "Logged out successfully";
//
//	}
	
	@PostMapping("/logout")
	public ResponseEntity<Map<String, String>> logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
	    if (authentication != null) {
	        new SecurityContextLogoutHandler().logout(request, response, authentication);
	    }
	    Map<String, String> responseBody = new HashMap<>();
	    responseBody.put("message", "Logged out successfully");
	    return ResponseEntity.ok(responseBody);
	}

	
	

}
