package com.classroom.virtualClassroom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.classroom.virtualClassroom.model.Session;
import com.classroom.virtualClassroom.repository.SessionRepository;

@Service
public class SessionService {
	
	@Autowired
    private SessionRepository sessionRepository;

    // Create a new session
    public Session createSession(Session newSession) {
        return sessionRepository.save(newSession);
    }

    // Get all sessions
    public List<Session> getAllSessions() {
        return sessionRepository.findAll();
    }
    
    // Get session by classname
    public List<Session> getSessionByClassName(String className) {
    	return sessionRepository.findByClassName(className);
    }
    
}
