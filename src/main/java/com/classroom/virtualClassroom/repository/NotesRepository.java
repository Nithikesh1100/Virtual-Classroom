package com.classroom.virtualClassroom.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.classroom.virtualClassroom.model.Notes;

public interface NotesRepository extends JpaRepository<Notes, Long>{

	List<Notes> findBySessionname(String sessionname);

}
