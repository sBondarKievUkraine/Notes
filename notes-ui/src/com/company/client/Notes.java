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
public class Notes implements EntryPoint {

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        final List<Note> noteList = new ArrayList();
        final List<Note> finedNotes = new ArrayList();

        final Button allButton = new Button("All");
        final Button findButton = new Button("Find");
        final Button putButton = new Button("Put");
        final Button getButton = new Button("Get");
        final Button delButton = new Button("Del");
        final Button updateButton = new Button("Update");

        final Label statusLabel = new Label();

        NotesService.App.getInstance().retrieveAllNotes(new getAllCallback(noteList));
        RootPanel rootPanel = RootPanel.get("main");
        final VerticalPanel mainVerticalPanel = new VerticalPanel();
        rootPanel.add(mainVerticalPanel);

        final VerticalPanel buttonVerticalPanel = new VerticalPanel();
        buttonVerticalPanel.add(allButton);
        buttonVerticalPanel.add(findButton);
        buttonVerticalPanel.add(putButton);
        buttonVerticalPanel.add(getButton);
        buttonVerticalPanel.add(delButton);
        buttonVerticalPanel.add(updateButton);
        RootPanel.get("button").add(buttonVerticalPanel);

        final VerticalPanel nameVerticalPanel = new VerticalPanel();
        RootPanel.get("name").add(nameVerticalPanel);
        final VerticalPanel bodyVerticalPanel = new VerticalPanel();
        RootPanel.get("body").add(bodyVerticalPanel);

        allButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                nameVerticalPanel.forEach(widget->widget.removeFromParent());
                bodyVerticalPanel.forEach(widget->widget.removeFromParent());
                for (Note note : noteList) {
                    nameVerticalPanel.add(new Label(note.getName()));
                    bodyVerticalPanel.add(new Label(note.getMessage()));
                }

            }
        });
        String keyWord = "keyword";
        findButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                nameVerticalPanel.forEach(widget->widget.removeFromParent());
                bodyVerticalPanel.forEach(widget->widget.removeFromParent());

                NotesService.App.getInstance().findByKeyWord(keyWord, new getAllCallback(finedNotes));
                for (Note note : finedNotes) {
                    nameVerticalPanel.add(new Label(note.getName()));
                    bodyVerticalPanel.add(new Label(note.getMessage()));

                }
            }
        });

        String putName = "Putted note name", msg = "Putted note body";
        putButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                NotesService.App.getInstance().putNote(putName, msg, new postAsyncCallback(statusLabel));
                mainVerticalPanel.add(statusLabel);

            }
        });

        String delName = "Putted note name";
        delButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                NotesService.App.getInstance().deleteNoteByName(delName, new postAsyncCallback(statusLabel));
                mainVerticalPanel.add(statusLabel);

            }
        });

        String updateName = "Putted note name", updmsg = "This note was updated";
        updateButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                NotesService.App.getInstance().updateNoteByName(updateName, updmsg, new postAsyncCallback(statusLabel));
                mainVerticalPanel.add(statusLabel);

            }
        });

    }

    private static class getAllCallback implements AsyncCallback<List<Note>> {
        private List<Note> noteList;

        public getAllCallback(List<Note> noteList) {
            this.noteList = noteList;
        }

        public void onFailure(Throwable throwable) {
            noteList.add(new Note("", "Error", "Failed to receive answer from server!"));
        }

        public void onSuccess(List<Note> result) {
            noteList.addAll(result);
        }
    }


    private static class postAsyncCallback implements AsyncCallback<String> {
        private Label label;

        public postAsyncCallback(Label label) {
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
