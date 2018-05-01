package com.company.server;

import com.company.client.Note;
import com.company.client.NotesService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NotesServiceImpl extends RemoteServiceServlet implements NotesService {

    /* private static List<Note> notesList = new ArrayList<>();

    static {
        //Initialize some Data
        notesList.add(new Note("6fdc3f7b-a59c-4dcc-b2b0-1d171d3880bb", "First note name", "First message body"));
        notesList.add(new Note("319c4140-e128-4c32-892b-0fb2ea7398fb", "Second note name", "Second message body"));
        notesList.add(new Note(UUID.randomUUID().toString(), "Third note name", "Third message body"));
        notesList.add(new Note(UUID.randomUUID().toString(), "Fourth note name", "Fourth message body"));
    }*/

    private static RestConnector restConnector = new RestConnector();

    /*private Note findNoteById(String id) {
    }

    private Note findNoteByName(String name) {
    }*/

    public String putNote(String noteName, String message) {
        return restConnector.PutNote(noteName, message);
    }

    public String deleteNoteByName(String delName) {
        return restConnector.delByName(delName)?"Note deleted":"Note is not deleted";
    }

    public List<Note> retrieveAllNotes() {
        return restConnector.retrieveAllNotes();
    }

    public List<Note> findByKeyWord(String keyWord) {
        return restConnector.retrieveByKeyWord(keyWord);
    }

    public String updateNoteByName(String param, String noteBody) {
        return restConnector.updateByName(param,noteBody)?"Note updated":"Note is not updated";
    }

     /*public Note getNoteById(String noteId) {
        return findNoteById(noteId);
    }

    public Note deleteNoteByName(String noteName) {
        return findNoteByName(noteName);
    }

    public void deleteNoteById(String noteId) {
    }

    public void deleteNoteByName(String noteName) {
    }



    public void updateNoteById(String param, String noteBody) {
    }

    public List<Date> whenUpdates(String noteId) {
    }*/

}