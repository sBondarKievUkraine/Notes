package com.company.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.List;

public interface NotesServiceAsync {
    void getMessage(String msg, AsyncCallback<String> async);

    void retrieveAllNotes(AsyncCallback<List<Note>> async);
}
