package com.company.controller;

import com.company.model.Note;
import com.company.service.NotesService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = NotesController.class, secure = false)
public class NotesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotesService notesService;


    @Before
    public void setUp() throws Exception {

//        Mockito.when(notesService.putNote(testNote.getName(), testNote.getMessage())).thenReturn(true);

        /*RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/putNote/"+ testNote.getName())
                .accept(MediaType.APPLICATION_JSON).content(testNote.getMessage())
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());*/

    }


    @Test
    public void getAllNotesTest() throws Exception {

//        String firstNoteJson = "{\"name\":\"First testNote\",\"message\":\"Hello first\",\"updates\":[\"01.01.18\",\"02.01.18\",\"03.01.18\",\"04.01.18\"]}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/allNotes")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());

//        assertEquals("http://localhost:9001/allNotes",response.getHeader(HttpHeaders.LOCATION));
    }

    private String testNoteJson = "{\"name\":\"First testNote\",\"message\":\"Hello first\",\"updates\":[\"01.01.18\",\"02.01.18\",\"03.01.18\",\"04.01.18\"]}";
    private  final Note testNote = new Note(UUID.randomUUID().toString(), "Note first", "First message");

    @Test
    public void getNoteByIdTest() throws Exception {
        RequestBuilder delRequestBuilder = MockMvcRequestBuilders
                .get("/getById/"+ testNote.getId())
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult delResult = mockMvc.perform(delRequestBuilder).andReturn();
        MockHttpServletResponse delResponse = delResult.getResponse();
        assertEquals(HttpStatus.OK.value(), delResponse.getStatus());
     }

    @Test
    public void getNoteByName()  throws Exception {
    }

    @Test
    public void deleteNoteTest() throws Exception {

        /*RequestBuilder putRequestBuilder = MockMvcRequestBuilders
                .put("/putNote/"+ testNote.getName())
                .accept(MediaType.APPLICATION_JSON).content(testNote.getMessage())
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult putResult = mockMvc.perform(putRequestBuilder).andReturn();
        MockHttpServletResponse response = putResult.getResponse();
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());*/

        RequestBuilder delRequestBuilder = MockMvcRequestBuilders
                .delete("/delByName/"+ testNote.getId())
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult delResult = mockMvc.perform(delRequestBuilder).andReturn();
        MockHttpServletResponse delResponse = delResult.getResponse();
        assertEquals(HttpStatus.NO_CONTENT.value(), delResponse.getStatus());
    }

    @Test
    public void putNoteTest() throws Exception {

        Mockito.when(notesService.putNote(testNote.getName(), testNote.getMessage())).thenReturn(testNote.getId());

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/putNote/"+ testNote.getName())
                .accept(MediaType.APPLICATION_JSON).content(testNote.getMessage())
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());

//        assertEquals("http://localhost:9001",response.getHeader(HttpHeaders.LOCATION));

    }

    @Test
    public void retrieveByPartOfBody() {
    }
}