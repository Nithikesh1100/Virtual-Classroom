package com.classroom.virtualClassroom.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.classroom.virtualClassroom.model.Notes;
import com.classroom.virtualClassroom.repository.NotesRepository;

@Service
public class NotesService {
	
	@Autowired
    private NotesRepository notesRepository;

    // Create or update a note
    public Notes saveOrUpdate(Notes note) {
        return notesRepository.save(note);
    }

    // Retrieve a note by ID
    public List<Notes> getNotesBySessionname(String sessionname) {
        return notesRepository.findBySessionname(sessionname);
    }

    // Retrieve all notes
    public List<Notes> getAllNotes() {
        return notesRepository.findAll();
    }

    // Delete a note by ID
    public void deleteNoteById(Long id) {
        notesRepository.deleteById(id);
    }
}
