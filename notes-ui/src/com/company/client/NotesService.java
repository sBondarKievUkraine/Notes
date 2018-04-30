package com.company.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.List;

@RemoteServiceRelativePath("NotesService")
public interface NotesService extends RemoteService {
    // Sample interface method of remote interface
    String getMessage(String msg);
    // Sample interface method of remote interface
    List<Note> retrieveAllNotes();

    List<Note> findByKeyWord(String key);


    public static class App {
        private static NotesServiceAsync ourInstance = GWT.create(NotesService.class);

        public static synchronized NotesServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
