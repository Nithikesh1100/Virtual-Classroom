package com.classroom.virtualClassroom.controller;

import java.io.IOException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.classroom.virtualClassroom.model.Lecture;
import com.classroom.virtualClassroom.model.Session;
import com.classroom.virtualClassroom.service.LectureService;

@RestController
@RequestMapping("/api/lectures")
@CrossOrigin(origins = "*")
public class LectureController {

    @Autowired
    private LectureService lectureService;
    
    private static final Logger logger = LoggerFactory.getLogger(LectureController.class);


    @PostMapping("/upload")
    public ResponseEntity<Lecture> uploadLecture(@RequestParam("lectureName") String lectureName,String className, 
                                                 @RequestParam("file") MultipartFile file) {
        try {
            Lecture savedLecture = lectureService.saveLecture(lectureName, className,file);
            return new ResponseEntity<>(savedLecture, HttpStatus.CREATED);
        } catch (IOException e) {
            logger.error("Error during file upload", e);  // Log the exact error details
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //API to get lecture by class name
    @GetMapping("/{classname}")
    public ResponseEntity<List<Lecture>> getSessionByClassName(@PathVariable String classname) {
        List<Lecture> lectures = lectureService.getLecture(classname);
        return ResponseEntity.ok(lectures);
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<Lecture>> getAllLectures() {
        List<Lecture> lectures = lectureService.getAllLectures();
        return new ResponseEntity<>(lectures, HttpStatus.OK);
    }
}
