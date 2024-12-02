package com.classroom.virtualClassroom.service;

import org.springframework.stereotype.Service;

import com.classroom.virtualClassroom.model.User;
import com.classroom.virtualClassroom.repository.UserRepository;

@Service
public class UserService {
	private UserRepository userRepository;

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}
