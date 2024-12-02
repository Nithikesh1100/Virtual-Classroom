package com.classroom.virtualClassroom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.classroom.virtualClassroom.model.Session;

public interface SessionRepository extends JpaRepository<Session, Long>{
	List<Session> findByClassName(String classname);
}
