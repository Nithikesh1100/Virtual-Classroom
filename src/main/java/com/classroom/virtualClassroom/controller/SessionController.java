package com.classroom.virtualClassroom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.classroom.virtualClassroom.model.Session;

import com.classroom.virtualClassroom.service.SessionService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/session")
public class SessionController {
	
	@Autowired
    private SessionService sessionService;

    // API to create a session
    @PostMapping
    public ResponseEntity<Session> createSession(@RequestBody Session newSession) {
        Session savedSession = sessionService.createSession(newSession);
        return ResponseEntity.ok(savedSession);
    }

    // API to get all sessions
    @GetMapping
    public ResponseEntity<List<Session>> getAllSessions() {
        List<Session> sessions = sessionService.getAllSessions();
        return ResponseEntity.ok(sessions);
    }
    
    //API to get session by classname
    @GetMapping("/{classname}")
    public ResponseEntity<List<Session>> getSessionByClassName(@PathVariable String classname) {
        List<Session> sessions = sessionService.getSessionByClassName(classname);
        return ResponseEntity.ok(sessions);
    }
}
