package com.classroom.virtualClassroom.model;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "lectures")
public class Lecture {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String lectureName;
	private String className;
	private String filePath;
	
	public Lecture(String lectureName,String className, String filePath) {
        this.lectureName = lectureName;
        this.className=className;
        this.filePath = filePath;
    }
}
