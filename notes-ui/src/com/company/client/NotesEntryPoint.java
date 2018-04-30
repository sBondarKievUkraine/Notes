package com.company.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class NotesEntryPoint implements EntryPoint {

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        final Button allButton = new Button("All");
        final Button findButton = new Button("Find");
        final Label name = new Label();
        final Label body = new Label();
        final List<Note> noteList = new ArrayList<>();


//        NotesService.App.getInstance().retrieveAllNotes(new getAllCllack(noteList));

        allButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                if (name.getText().equals("")) {
//                    NotesService.App.getInstance().getMessage("Hello, World!", new MyAsyncCallback(name));
                    NotesService.App.getInstance().retrieveAllNotes(new getAllCllack(noteList));
                    StringBuilder names= new StringBuilder(),bodys= new StringBuilder();
                    for (Note note : noteList) {
                        names.append(note.getName()+"\n");
                        names.append("\n");
                        bodys.append(note.getMessage()+"\n");
                        bodys.append("\n");

                    }
                    name.setText(names.toString());
                    body.setText(bodys.toString());
                } else {
                    name.setText("");
                    body.setText("");
                }
            }
        });
        String keyWord = "Second";
        findButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                if (name.getText().equals("")) {
                    NotesService.App.getInstance().findByKeyWord(keyWord,new getAllCllack(noteList));
//                    NotesService.App.getInstance().getMessage("Hello, World!", new MyAsyncCallback(name));

                    StringBuilder names= new StringBuilder(),bodys= new StringBuilder();
                    for (Note note : noteList) {
                        names.append(note.getName()+"\n");
                        names.append("\n");
                        bodys.append(note.getMessage()+"\n");
                        bodys.append("\n");

                    }
                    name.setText(names.toString());
                    body.setText(bodys.toString());
                } else {
                    name.setText("");
                    body.setText("");
                }
            }
        });

        RootPanel.get("button").add(allButton);
        RootPanel.get("button").add(findButton);
        RootPanel.get("name").add(name);
        RootPanel.get("body").add(body);
    }

    private static class getAllCllack implements AsyncCallback<List<Note>> {
        private List<Note> noteList;

        public getAllCllack(List<Note> noteList) {
            this.noteList = noteList;
        }

        public void onFailure(Throwable throwable) {
            noteList.add(new Note("","Error","Failed to receive answer from server!"));
        }

        public void onSuccess(List<Note> result) {
            noteList.addAll(result);
        }
    }


    private static class MyAsyncCallback implements AsyncCallback<String> {
        private Label label;

        public MyAsyncCallback(Label label) {
            this.label = label;
        }

        public void onSuccess(String result) {
            label.getElement().setInnerHTML(result);
        }

        public void onFailure(Throwable throwable) {
            label.setText("Failed to receive answer from server!");
        }
    }
}
