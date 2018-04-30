package com.company.model;

import org.codehaus.jackson.annotate.JsonCreator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Note {
    private String id;
    private String name;
    private String message;
    private List<Date> updateDates;

    @JsonCreator
    public Note(String id, String name, String message) {
        super();
        this.id = id;
        this.name = name;
        this.message = message;
        this.updateDates = new ArrayList<>();
        updateDates.add(new Date());
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
        updateDates.add(new Date());
        this.message = message;
    }

    public List<Date> getUpdateDates() {
        return updateDates;
    }

    @Override
    public String toString() {
        return String.format(
                "Note [id=%s, name=%s, message=%s, updateDates=%s]", id, name, message, updateDates);
    }
}