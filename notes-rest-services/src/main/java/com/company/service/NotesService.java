package com.company.service;

import com.company.exception.NoteNotFoundException;
import com.company.model.Note;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.*;

import static java.lang.String.format;


@Component
public class NotesService {

    private static List<Note> notesList = Lists.newArrayList();

    static {
        //Initialize some Data
        notesList.add(new Note("6fdc3f7b-a59c-4dcc-b2b0-1d171d3880bb", "First note name", "First message body"));
        notesList.add(new Note("319c4140-e128-4c32-892b-0fb2ea7398fb", "Second note name", "Second message body"));
        notesList.add(new Note(UUID.randomUUID().toString(), "Third note name", "Third message body"));
        notesList.add(new Note(UUID.randomUUID().toString(), "Fourth note name", "Fourth message body"));
    }

    private Note findNoteById(String id) {
        return notesList
                .stream()
                .filter(nl -> nl.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoteNotFoundException((format("Cannot find note with id: \"%s\"", id))));
    }

    private Note findNoteByName(String name) {
        return notesList
                .stream()
                .filter(nl -> nl.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new NoteNotFoundException((format("Cannot find note with name: \"%s\"", name))));
    }

    public String putNote(String noteName, String message) {
        // ToDo resolve conflicts when notes will has same names
        Note note = new Note(UUID.randomUUID().toString(), noteName, message);
        final boolean added = notesList.add(note);
        if (added) {
            return note.getId();
        } else {
            throw new RuntimeException(format("Cannot save data note with name: \"%s\"", noteName));
        }
    }

    public List<Note> retrieveAllNotes() {
        return notesList;
    }

    public List<Note> findByKeyWord(String keyWord) {
        List<Note> foundNotes = Lists.newArrayList();
        for (Note note : NotesService.notesList) {
            if (note.getMessage().contains(keyWord)) {
                foundNotes.add(note);
            }
        }
        if (foundNotes.isEmpty()) {
            throw new NoteNotFoundException((format("Cannot find note by key word \"%s\"", keyWord)));
        } else {
            return foundNotes;
        }
    }

    public Note getNoteById(String noteId) {
        return findNoteById(noteId);
    }

    public Note getNoteByName(String noteName) {
        return findNoteByName(noteName);
    }

    public void deleteNoteById(String noteId) {
        for (Note note : notesList) {
            if (note.getId().equals(noteId)) {
                notesList.remove(note);
                return;
            }
        }
        throw new NoteNotFoundException((format("Cannot find note with id: %s", noteId)));
    }

    public void deleteNoteByName(String noteName) {
        for (Note note : NotesService.notesList) {
            if (note.getName().equals(noteName)) {
                NotesService.notesList.remove(note);
            }
        }
        throw new NoteNotFoundException((format("Cannot find note with name: %s", noteName)));
    }

    public void updateNoteByName(String param, String noteBody) {
        for (Note note : notesList) {
            if (note.getName().equals(param)) {
                notesList.remove(note);
                note.setMessage(noteBody);
                notesList.add(note);
                return;
            }
        }
        throw new NoteNotFoundException((format("Cannot find note with name: %s", param)));
    }

    public void updateNoteById(String param, String noteBody) {
        //Optional<Note> note = findNoteByName(param);
        for (Note note : notesList) {
            if (note.getId().equals(param)) {
                notesList.remove(note);
                note.setMessage(noteBody);
                notesList.add(note);
                return;
            }
        }
        throw new NoteNotFoundException((format("Cannot find note with id: %s", param)));
    }

    public List<Date> whenUpdates(String noteId) {
        Note note = getNoteById(noteId);
        if (note == null) {
            return null;
        }
        return note.getUpdateDates();
    }
}