package com.company.controller;

import com.company.service.NotesService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class NotesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotesService notesService;


 /*   @Before
    public void setUp() throws Exception {
        *//*Course mockCourse = new Course("Course1", "Spring", "10Steps",
                Arrays.asList("Learn Maven", "Import Project", "First Example",
                        "Second Example"));*//*

        String firstNoeteJson = "{\"name\":\"First note\",\"message\":\"Hello first\",\"updates\":[\"01.01.18\",\"02.01.18\",\"03.01.18\",\"04.01.18\"]}";

    }

    @After
    public void tearDown() throws Exception {
    }*/

    @Test
    public void getAllNotes() {
    }

    @Test
    public void getNoteById() {
    }

    @Test
    public void getNoteByName() {
    }

    @Test
    public void deleteNote() {
    }

    @Test
    public void putNoteTest() throws Exception {

        String firstNoteJson = "{\"name\":\"First note\",\"message\":\"Hello first\",\"updates\":[\"01.01.18\",\"02.01.18\",\"03.01.18\",\"04.01.18\"]}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/notes/01")
                .accept(MediaType.APPLICATION_JSON).content(firstNoteJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());

        assertEquals("http://localhost:9001/notes/01",
                response.getHeader(HttpHeaders.LOCATION));

    }

    @Test
    public void retrieveByPartOfBody() {
    }
}