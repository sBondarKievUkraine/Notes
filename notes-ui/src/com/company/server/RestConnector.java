package com.company.server;

import com.company.client.Note;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class RestConnector {
    private static final Logger logger = Logger.getLogger(RestConnector.class);

    private static final String TEST_ENDPOINT = "TEST_ENDPOINT";
    private static final String TEST_USER = "TEST_USER";
    private static final String TEST_PASSWORD = "TEST_PASSWORD";

    private static String endpoint;
    private static String user;
    private static String userPassword;

    public RestConnector() {
    }

    static {
        endpoint = System.getProperty(TEST_ENDPOINT);
        if (endpoint == null) endpoint = System.getenv(TEST_ENDPOINT);
        if (endpoint == null) throw new IllegalArgumentException("Endpoint is not specified.");
        logger.info(String.format("Endpoint used - %s", endpoint));
        user = System.getProperty(TEST_USER);
        if (user == null) user = System.getenv(TEST_USER);
        if (user == null) throw new IllegalArgumentException("User is not specified.");
        logger.debug(String.format("Email used - %s", user));
        userPassword = System.getProperty(TEST_PASSWORD);
        if (userPassword == null) userPassword = System.getenv(TEST_PASSWORD);
        if (userPassword == null) throw new IllegalArgumentException("User password is not specified.");
    }

    public List<Note> retrieveAllNotes() {
        try {
            final HttpResponse<String> response =
                    Unirest.get(endpoint + "/getAll") //ToDO Add after implement authorization
//                                                    .header("x-access-token", authToken)
                            .header(HttpHeaders.CONTENT_TYPE, "application/json")
                            .asObject(String.class);
            return deSerializeList(response.getBody());
        } catch (UnirestException e) {
            logger.error(e);
            e.printStackTrace();
            throw new IllegalStateException("Auth is not successful. Was not able to get result");
        }
    }

    private List<Note> deSerializeList(String response) {
        //ToDo Implement deserialization of ArrayList is more pretty
        logger.debug(String.format("Response is %s", response));
        StringBuilder responseBody = new StringBuilder(response);
        responseBody.insert(0, "{\"notes\":");
        responseBody.append("}");
        ArrayList<Note> noteArrayList = new ArrayList<>();
        //ToDo When only one object LinkedTreeMap cannot be cast to java.util.ArrayList
        for (LinkedTreeMap restNoteList : (ArrayList<LinkedTreeMap>)
                new Gson().fromJson(responseBody.toString(), LinkedTreeMap.class).get("notes")) {
//                Note note = new Gson().fromJson(restNoteList.toString(), Note.class);
            noteArrayList.add(new Note(restNoteList.get("id").toString(),
                    restNoteList.get("name").toString(),
                    restNoteList.get("message").toString()));
        }
        return noteArrayList;
    }

    public List<Note> retrieveByKeyWord(String keyWord) {
        try {
            final HttpResponse<String> response =
                    Unirest.post(endpoint + "/retrieveByPartOfBody")
//                                                    .header("x-access-token", authToken)
                            .header(HttpHeaders.CONTENT_TYPE, "application/json")
                            .body(keyWord)
                            .asObject(String.class);
            return deSerializeList(response.getBody());
        } catch (UnirestException e) {
            logger.error(e);
            e.printStackTrace();
            throw new IllegalStateException("Auth is not successful. Was not able to get result");
        }
    }

    public String PutNote(String name, String msg) {
        try {
            final HttpResponse<String> response =
                    Unirest.put(String.format("%s/putNote/%s", endpoint, name))
//                                                    .header("x-access-token", authToken)
                            .header(HttpHeaders.CONTENT_TYPE, "application/json")
                            .body(msg)
                            .asObject(String.class);
            logger.debug("Putted note " + response.getBody());
            return response.getBody();
        } catch (UnirestException e) {
            logger.error(e);
            e.printStackTrace();
            throw new IllegalStateException("Auth is not successful. Was not able to get result");
        }
    }

    public boolean delByName(String name) {
        try {
            final HttpResponse<String> response =
                    Unirest.delete(String.format("%s/delByName/%s", endpoint, name))
//                                                    .header("x-access-token", authToken)
                            .header(HttpHeaders.CONTENT_TYPE, "application/json")
                            .asObject(String.class);
            logger.debug("Deleted note \"" + name + "\"");
            return response.getStatus()==HttpStatus.SC_NO_CONTENT;
        } catch (UnirestException e) {
            logger.error(e);
            e.printStackTrace();
            throw new IllegalStateException("Auth is not successful. Was not able to get result");
        }
    }

    public boolean updateByName(String name, String body) {
        try {
            final HttpResponse<String> response =
                    Unirest.post(String.format("%s/updateByName/%s", endpoint, name))
//                                                    .header("x-access-token", authToken)
                            .header(HttpHeaders.CONTENT_TYPE, "application/json")
                            .body(body)
                            .asObject(String.class);
            logger.debug("Updated note \"" + name + "\" has new body \"" + body+ "\"");
            return response.getStatus()==HttpStatus.SC_OK;
        } catch (UnirestException e) {
            logger.error(e);
            e.printStackTrace();
            throw new IllegalStateException("Auth is not successful. Was not able to get result");
        }

    }
}
