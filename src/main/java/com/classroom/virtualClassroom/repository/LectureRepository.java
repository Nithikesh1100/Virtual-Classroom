package com.classroom.virtualClassroom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.classroom.virtualClassroom.model.Lecture;

public interface LectureRepository extends JpaRepository<Lecture, Long>{
	List<Lecture> findByClassName(String classname);
}
