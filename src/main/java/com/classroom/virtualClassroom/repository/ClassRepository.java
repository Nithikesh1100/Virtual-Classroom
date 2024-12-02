package com.classroom.virtualClassroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.classroom.virtualClassroom.model.Class;

public interface ClassRepository extends JpaRepository<Class, Long>{

}
