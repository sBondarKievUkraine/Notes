package com.company.server;

import com.company.client.Note;
import com.company.client.NotesService;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NotesServiceImpl extends RemoteServiceServlet implements NotesService {

    private static List<Note> notesList = new ArrayList<>();

    static {
        //Initialize some Data
        notesList.add(new Note("6fdc3f7b-a59c-4dcc-b2b0-1d171d3880bb", "First note name", "First message body"));
        notesList.add(new Note("319c4140-e128-4c32-892b-0fb2ea7398fb", "Second note name", "Second message body"));
        notesList.add(new Note(UUID.randomUUID().toString(), "Third note name", "Third message body"));
        notesList.add(new Note(UUID.randomUUID().toString(), "Fourth note name", "Fourth message body"));
    }

    private static RestConnector restConnector = new RestConnector();

    /*private Note findNoteById(String id) {
    }

    private Note findNoteByName(String name) {
    }

    public String putNote(String noteName, String message) {
    }*/

    /*public String retrieveAllNotes() {
        return notesList;
    }*/

    public String getMessage(String msg) {
        return "getMessage";
    }

    public List<Note> retrieveAllNotes() {
        return restConnector.retrieveAllNotes();
    }

    public List<Note> findByKeyWord(String keyWord) {
        return null;
//        return restConnector.retrieveByKeyWord(keyWord);
    }

     /*public Note getNoteById(String noteId) {
        return findNoteById(noteId);
    }

    public Note getNoteByName(String noteName) {
        return findNoteByName(noteName);
    }

    public void deleteNoteById(String noteId) {
    }

    public void deleteNoteByName(String noteName) {
    }

    public void updateNoteByName(String param, String noteBody) {
    }

    public void updateNoteById(String param, String noteBody) {
    }

    public List<Date> whenUpdates(String noteId) {
    }*/

}