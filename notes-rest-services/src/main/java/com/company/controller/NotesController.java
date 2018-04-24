package com.company.controller;

import java.util.List;

import com.company.model.Note;
import com.company.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class NotesController {

    @Autowired
    private NotesService notesService;

    /*@GetMapping("/updates/{noteId}/")
    public List<Date> retrieveAllUpdateForNote(@PathVariable String noteId) {
        return notesService.whenUpdates(noteId);
    }*/


    @GetMapping("/allNotes")
    public List<Note> getAllNotes() {
        return notesService.retrieveAllNotes();
    }

    @GetMapping("/getById/{id}")
    public Note getNoteById(@PathVariable("id") String id) {
        return notesService.getNoteById(id);
    }

    @GetMapping("/getByName/{name}")
    public Note getNoteByName(@PathVariable("name") String name) {
        return notesService.getNoteById(name);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteNote(@PathVariable("id") String id) {
        notesService.deleteNoteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{noteName}/")
    public ResponseEntity<Void> putNote(@PathVariable String noteName, @RequestBody String noteBody) {
        notesService.putNote(noteName, noteBody);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/findNotes/")
    public List<Note> retrieveByPartOfBody(@RequestBody String noteBody) {
        return notesService.findByKeyWord(noteBody);
    }
}
