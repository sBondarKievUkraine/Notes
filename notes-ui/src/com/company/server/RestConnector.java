package com.company.server;

import com.company.client.Note;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.http.HttpHeaders;
import org.apache.log4j.Logger;

import javax.management.AttributeList;
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

    public static void main(String[] args) {

        try {
            RestConnector restConnector = new RestConnector();
            restConnector.retrieveAllNotes();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static List<Note> retrieveAllNotes() {

        ArrayList<Note> noteArrayList = new ArrayList<>();
        try {

            final HttpResponse<String> response =
                    Unirest.get(endpoint + "/getAll")
//                                                    .header("x-access-token", authToken)
                            .header(HttpHeaders.CONTENT_TYPE, "application/json")
                            //                        .body(new Gson().toJson(strings))
                            .asObject(String.class);
            //ToDo Implement  deserialization of ArrayList
            StringBuilder responseBody = new StringBuilder(response.getBody());
            responseBody.insert(0,"{\"notes\":");
            responseBody.append("}");
            LinkedTreeMap linkedTreeMap = new Gson().fromJson(responseBody.toString(), LinkedTreeMap.class);
            logger.debug(String.format("Response is %s", responseBody));
            for (LinkedTreeMap restNoteList : (ArrayList<LinkedTreeMap>) linkedTreeMap.get("notes")) {
//                Note note = new Gson().fromJson(restNoteList.toString(), Note.class);
                noteArrayList.add(new Note(restNoteList.get("id").toString(),
                        restNoteList.get("name").toString(),
                        restNoteList.get("message").toString()));
            }
            /*if (linkedTreeMap == null) {
            //ToDo return empty list
            return null;
            }*/
        } catch (UnirestException e) {
            logger.error(e);
            e.printStackTrace();
            throw new IllegalStateException("Auth is not successful. Was not able to get result");
        }
        return noteArrayList;
    }

    public static List<Note> retrieveByKeyWord(String keyWord) {
        List<Note> noteArrayList = new ArrayList<>();
        try {
            final HttpResponse<String> response =
                    Unirest.post(endpoint + "/findNotes")
//                                                    .header("x-access-token", authToken)
                            .header(HttpHeaders.CONTENT_TYPE, "application/json")
                            .body(keyWord)
                            .asObject(String.class);
            //ToDo Implement  deserialization of ArrayList
            StringBuilder responseBody = new StringBuilder(response.getBody());
            responseBody.insert(0,"{\"notes\":");
            responseBody.append("}");
            LinkedTreeMap linkedTreeMap = new Gson().fromJson(responseBody.toString(), LinkedTreeMap.class);
            logger.debug(String.format("Response is %s", responseBody));
            for (LinkedTreeMap restNoteList : (ArrayList<LinkedTreeMap>) linkedTreeMap.get("notes")) {
//                Note note = new Gson().fromJson(restNoteList.toString(), Note.class);
                noteArrayList.add(new Note(restNoteList.get("id").toString(),
                        restNoteList.get("name").toString(),
                        restNoteList.get("message").toString()));
            }
            /*if (linkedTreeMap == null) {
            //ToDo return empty list
            return null;
            }*/
        } catch (UnirestException e) {
            e.printStackTrace();
            throw new IllegalStateException("Auth is not successful. Was not able to get result");
        }
        return noteArrayList;
    }
}
