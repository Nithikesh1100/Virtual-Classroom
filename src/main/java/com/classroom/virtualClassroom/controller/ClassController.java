package com.classroom.virtualClassroom.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.classroom.virtualClassroom.service.ClassService;

import lombok.RequiredArgsConstructor;

import com.classroom.virtualClassroom.model.Class;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/classes")
@RequiredArgsConstructor
public class ClassController {
	
	@Autowired
    private ClassService classService;

    // API to create a class
    @PostMapping
    public ResponseEntity<Class> createClass(@RequestBody Class newClass) {
        Class savedClass = classService.createClass(newClass);
        return ResponseEntity.ok(savedClass);
    }

    // API to get all classes
    @GetMapping
    public ResponseEntity<List<Class>> getAllClasses() {
        List<Class> classes = classService.getAllClasses();
        return ResponseEntity.ok(classes);
    }

}
