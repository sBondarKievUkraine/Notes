package com.company.controller;

import com.company.model.Note;
import com.company.service.NotesService;
import org.junit.Before;
import org.junit.BeforeClass;
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
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = NotesController.class, secure = false)
public class NotesControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private NotesService notesService;

    private RequestBuilder requestBuilder;

    private List<Note> testNoteList = new ArrayList<Note>() {{
        //Initialize some Data
        add(new Note("6fdc3f7b-a59c-4dcc-b2b0-1d171d3880bb", "First note name", "First message body"));
        add(new Note("319c4140-e128-4c32-892b-0fb2ea7398fb", "Second note name", "Second message body"));
        add(new Note(UUID.randomUUID().toString(), "Third note name", "Third message body"));
        add(new Note(UUID.randomUUID().toString(), "Fourth note name", "Fourth message body"));

        add(new Note(UUID.randomUUID().toString(), "Absent note ", "Something"));
    }};

    @Test
    public void putNoteTest() throws Exception {
        for (Note note : testNoteList) {
            Mockito.when(notesService.putNote(note.getName(), note.getMessage())).thenReturn(note.getId());
            putNote(note);
            MvcResult result = mockMvc.perform(requestBuilder).andDo(print()).andExpect(status().isCreated()).andReturn();

            MockHttpServletResponse response = result.getResponse();

            assertEquals(HttpStatus.CREATED.value(), response.getStatus());
            final String createdUid = result.getResponse().getContentAsString();
            UUID expectedUid = UUID.fromString(createdUid);
            assertEquals(expectedUid.toString(), createdUid);
        }
    }

    private void putNote(Note note) throws Exception {
        requestBuilder = MockMvcRequestBuilders
                .put("/putNote/" + note.getName())
                .accept(MediaType.APPLICATION_JSON).content(note.getMessage())
                .contentType(MediaType.APPLICATION_JSON);
    }


    @Before
    public void setUp() throws Exception {
        //Filling data before testing. Only 4 objects.
        for (int i = 0; i < testNoteList.size() - 1; i++) {
            putNote(testNoteList.get(i));
        }
    }


    @Test
    public void getAllNotesTest() throws Exception {


        requestBuilder = MockMvcRequestBuilders
                .get("/getAll")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andDo(print()).andExpect(status().isOk()).andReturn();

        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void getNoteByIdTest() throws Exception {
        requestBuilder = MockMvcRequestBuilders
                .get("/getById/" + testNoteList.get(0).getId())
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void getNoteByName() throws Exception {
    }

    @Test
    public void deleteNoteTest() throws Exception {
        RequestBuilder delRequestBuilder = MockMvcRequestBuilders
                .delete("/delByName/" + testNoteList.get(0).getId())
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult delResult = mockMvc.perform(requestBuilder).andDo(print()).andExpect(status().isNoContent()).andReturn();
        MockHttpServletResponse delResponse = delResult.getResponse();
        assertEquals(HttpStatus.NO_CONTENT.value(), delResponse.getStatus());

    }

    @Test
    public void retrieveByPartOfBody() {
        //ToDo implement test
    }
}