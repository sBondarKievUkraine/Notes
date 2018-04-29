package com.company.client;

import com.google.gwt.user.client.rpc.IsSerializable;

public abstract class AbstractNote implements IsSerializable {
    private String id;
    private String name;
    private String message;

    protected AbstractNote() {
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