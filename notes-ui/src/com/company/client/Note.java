package com.company.client;

public class Note extends AbstractNote {
    private String id;
    private String name;
    private String message;

    public Note(String id, String name, String message) {
        super();
        this.id = id;
        this.name = name;
        this.message = message;
    }

    public Note() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}