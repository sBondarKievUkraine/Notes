package com.company.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Note extends AbstractNote{
    private String id;
    private String name;
    private String message;
    private List<Date> updateDates;

    public Note() {

    }
    public Note(String id, String name, String message) {
        super();
        this.id = id;
        this.name = name;
        this.message = message;
        this.updateDates = new ArrayList<>();
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

    public List<Date> getUpdateDates() {
        return updateDates;
    }

}