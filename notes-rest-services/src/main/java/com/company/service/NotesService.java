package com.company.service;

import com.company.exception.NoteNotFoundException;
import com.company.model.Note;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.lang.String.format;


@Component
public class NotesService {

    private static List<Note> notesList = Lists.newArrayList();

    private Optional <Note> findNoteByName(String name) {
        return notesList
                .stream()
                .filter(nl -> nl.getId().equals(name))
                .findFirst();
    }

    public Note putNote(String noteName, String message) {
        // ToDo resolve conflicts when notes will has same names
        Note note = new Note(UUID.randomUUID().toString(), noteName, message);
        final boolean added = notesList.add(note);
        if (added) {
            return note;
        }

        throw new RuntimeException(format("Cannot save data note with name: %s", noteName));
    }


    public List<Note> retrieveAllNotes() {
        return notesList;
    }

    public List<Note> findByKeyWord(String keyWord) {
        List<Note> foundNotes = Lists.newArrayList();
        for (Note note : NotesService.notesList) {
            if (note.getMessage().compareTo(keyWord)>0) {
                foundNotes.add(note);
            }
        }
        if (foundNotes.isEmpty()){
            throw new NoteNotFoundException((format("Cannot find note by key word id: %s", keyWord)));
        } else {
            return foundNotes;
        }
    }
    private Note findNoteById(String id) {
        return notesList
                .stream()
                .filter(nl -> nl.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoteNotFoundException((format("Cannot find note with id: %s", id))));
    }

    public Note getNoteById(String noteId) {
        return findNoteById(noteId);
    }

    public void deleteNoteById(String noteId) {
        for (Note note : NotesService.notesList) {
            if (note.getId().equals(noteId)) {
                NotesService.notesList.remove(note);
                return;
            }
        }
        throw new NoteNotFoundException((format("Cannot find note with id: %s", noteId)));
    }

    public Note getNoteByName(String noteName) {
        for (Note note : NotesService.notesList) {
            if (note.getName().equals(noteName)) {
                return note;
            }
        }
        throw new NoteNotFoundException((format("Cannot find note with name: %s", noteName)));
    }

    public void deleteNoteByName(String noteName) {
        for (Note note : NotesService.notesList) {
            if (note.getName().equals(noteName)) {
                NotesService.notesList.remove(note);
            }
        }
        throw new NoteNotFoundException((format("Cannot find note with name: %s", noteName)));
    }

    public List<Date> whenUpdates(String noteId) {
        Note note = getNoteById(noteId);

        if (note == null) {
            return null;
        }

        return note.getUpdateDates();
    }

}