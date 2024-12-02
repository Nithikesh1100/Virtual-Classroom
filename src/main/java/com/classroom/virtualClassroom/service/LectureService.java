package com.classroom.virtualClassroom.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.classroom.virtualClassroom.model.Lecture;
import com.classroom.virtualClassroom.repository.LectureRepository;



@Service
public class LectureService {
	
	@Autowired
	LectureRepository lectureRepository;

	private static final String UPLOAD_DIR = "uploads/"; // Folder inside the project

    public Lecture saveLecture(String lectureName,String className, MultipartFile file) throws IOException {
        // Get the original filename
        String fileName = file.getOriginalFilename();
        Path uploadPath = Paths.get(UPLOAD_DIR);

        // Ensure the directory exists
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath); // Create the folder if it doesn't exist
        }

        // Build the full file path and save the file
        Path filePath = uploadPath.resolve(fileName);
        
        // Write the file to the target location
        Files.write(filePath, file.getBytes());

        // Save lecture data in the database (assuming Lecture model and repository are correct)
        Lecture lecture = new Lecture(lectureName,className,filePath.toString());
        return lectureRepository.save(lecture);
    }

    public List<Lecture> getLecture(String classname) {
        return lectureRepository.findByClassName(classname);
    }
    
    public List<Lecture> getAllLectures() {
        return lectureRepository.findAll();
    }
}