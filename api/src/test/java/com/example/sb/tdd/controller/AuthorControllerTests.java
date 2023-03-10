package com.example.sb.tdd.controller;

import com.example.sb.tdd.model.Author;
import com.example.sb.tdd.service.AuthorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class AuthorControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AuthorService service;

    @Test
    public void testGetAuthors() throws Exception {
        when(service.getAuthors()).thenReturn(List.of(
                Author.builder().id(1).name("n1").build(),
                Author.builder().id(2).name("n2").build()));

        mockMvc.perform(get("/api/author")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void testGetAuthor() throws Exception {
        when(service.getAuthor(1)).thenReturn(Author.builder().id(1).name("n1").build());

        mockMvc.perform(get("/api/author/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("n1")));
    }

    @Test
    public void testCreateAuthor() throws Exception {
        when(service.createAuthor(any(Author.class))).thenReturn(Author.builder().id(1).name("n1").build());

        mockMvc.perform(post("/api/author")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content((new ObjectMapper()).writeValueAsString(Author.builder().id(1).name("n1").build())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo("n1")));
    }

    @Test
    public void testUpdateAuthorName() throws Exception {
        when(service.updateAuthor(anyInt(), any(Author.class))).thenReturn(Author.builder().id(1).name("n1").build());

        mockMvc.perform(patch("/api/author/1/name")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content((new ObjectMapper()).writeValueAsString(Author.builder().id(1).name("n1").build())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("n1")));
    }

    @Test
    public void testDeleteAuthor() throws Exception {
        doNothing().when(service).deleteAuthor(anyInt());

        mockMvc.perform(delete("/api/author/1"))
                .andExpect(status().isNoContent());
    }

}
