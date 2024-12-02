package com.classroom.virtualClassroom.repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

import com.classroom.virtualClassroom.model.User;

public interface UserRepository extends JpaRepository<User,Long>{
	Optional<User> findByEmail(String email);
	Boolean existsByEmail(String username);
	Optional<User> findById(Long userId);
}
