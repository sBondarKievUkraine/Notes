package com.company.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.List;

public interface NotesServiceAsync {

    void retrieveAllNotes(AsyncCallback<List<Note>> async);

    void findByKeyWord(String key, AsyncCallback<List<Note>> async);

    void putNote(String name, String msg, AsyncCallback<String> async);

    void deleteNoteByName(String delName, AsyncCallback<String> async);

    void updateNoteByName(String updateName, String updateMsg, AsyncCallback<String> async);
}
