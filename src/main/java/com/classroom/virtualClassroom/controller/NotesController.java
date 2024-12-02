package com.classroom.virtualClassroom.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.classroom.virtualClassroom.model.Notes;
import com.classroom.virtualClassroom.service.NotesService;

@RestController
@RequestMapping("/api/notes")
@CrossOrigin(origins = "*")
public class NotesController {
	
	@Autowired
    private NotesService notesService;

    // Create or update a note
    @PostMapping
    public ResponseEntity<Notes> createOrUpdateNote(@RequestBody Notes note) {
        Notes savedNote = notesService.saveOrUpdate(note);
        return ResponseEntity.ok(savedNote);
    }

    @GetMapping("/session/{sessionname}")
    public ResponseEntity<List<Notes>> getNotesBySessionName(@PathVariable String sessionname) {
        List<Notes> notes = notesService.getNotesBySessionname(sessionname);
        if (notes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(notes);
    }


    // Get all notes
    @GetMapping
    public ResponseEntity<List<Notes>> getAllNotes() {
        List<Notes> notesList = notesService.getAllNotes();
        return ResponseEntity.ok(notesList);
    }

    // Delete a note by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNoteById(@PathVariable Long id) {
        notesService.deleteNoteById(id);
        return ResponseEntity.noContent().build();
    }
}
