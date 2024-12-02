package com.classroom.virtualClassroom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.classroom.virtualClassroom.repository.ClassRepository;
import com.classroom.virtualClassroom.model.Class;

@Service
public class ClassService {
	
	@Autowired
    private ClassRepository classRepository;

    // Create a new class
    public Class createClass(Class newClass) {
        return classRepository.save(newClass);
    }

    // Get all classes
    public List<Class> getAllClasses() {
        return classRepository.findAll();
    }
}
