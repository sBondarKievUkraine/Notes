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


    @PutMapping("/putNote/{noteName}")
    public ResponseEntity<String> putNote(@PathVariable String noteName, @RequestBody String noteBody) {
        String noteId = notesService.putNote(noteName, noteBody);
        return ResponseEntity.status(HttpStatus.CREATED).body(noteId);
    }

    /*@GetMapping("/updates/{noteId}/")
    public List<Date> retrieveAllUpdateForNote(@PathVariable String noteId) {
        return notesService.whenUpdates(noteId);
    }*/

    @GetMapping("/allNotes")
    public List<Note> getAllNotes() {
        return notesService.retrieveAllNotes();
    }

    @GetMapping("/getById/{param}")
    public Note getNoteById(@PathVariable("param") String param) {
        return notesService.getNoteById(param);
    }

    @GetMapping("/getByName/{param}")
    public Note getNoteByName(@PathVariable("param") String param) {
        return notesService.getNoteByName(param);
    }

    @DeleteMapping("/delByName/{param}")
    public ResponseEntity delByName(@PathVariable("param") String param) {
        notesService.deleteNoteByName(param);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/delById/{param}")
    public ResponseEntity delById(@PathVariable("param") String param) {
        notesService.deleteNoteById(param);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/findNotes")
    public List<Note> retrieveByPartOfBody(@RequestBody String noteBody) {
        return notesService.findByKeyWord(noteBody);
    }
}
