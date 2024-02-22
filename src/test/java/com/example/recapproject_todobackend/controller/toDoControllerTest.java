package com.example.recapproject_todobackend.controller;

import com.example.recapproject_todobackend.model.toDo;
import com.example.recapproject_todobackend.model.toDoStatus;
import com.example.recapproject_todobackend.repository.toDoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class toDoControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private toDoRepository repo;

    @Test
    void getAlltoDo() throws Exception {
        toDo todo = new toDo("1", "message1", toDoStatus.OPEN);
        repo.save(todo);

        mvc.perform(get("/api/todo"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        """
                                [
                                 {
                                     "id": "1",
                                     "description": "message1",
                                     "status": "OPEN"
                                 }
                                ]
                                """
                ));
    }

    @Test
    void getById() throws Exception {
        //GIVEN
        toDo existingTodo = new toDo("1", "message1", toDoStatus.OPEN);
        repo.save(existingTodo);

        //WHEN
        mvc.perform(get("/api/todo/1"))
                //THEN
                .andExpect(status().isOk())
                .andExpect(content().json("""
                            {
                                "id": "1",
                                "description": "message1",
                                "status": "OPEN"
                            }
                        """));
    }

    @Test
    void saveNew() throws Exception {
        //GIVEN
        //WHEN & THEN
        mvc.perform(MockMvcRequestBuilders.post("/api/todo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "description": "message1"
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "description": "message1"
                        }
                        """
                ))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
    void delete() throws Exception {
        //GIVEN
        toDo newtoDo = new toDo("1", "message1",toDoStatus.OPEN);
        repo.save(newtoDo);
        //WHEN & THEN
        mvc.perform(MockMvcRequestBuilders.delete("/api/todo/1"))
                .andExpect(status().isOk());

        mvc.perform(get("/api/todo"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    void update() throws Exception {
        //GIVEN
        toDo existingTodo = new toDo("1", "test-description", toDoStatus.OPEN);
        repo.save(existingTodo);

        //WHEN
        mvc.perform(put("/api/todo/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                    {
                                        "description": "test-description-2",
                                        "status": "IN_PROGRESS"
                                    }
                                """))
                //THEN
                .andExpect(status().isOk())
                .andExpect(content().json("""
                            {
                                "id": "1",
                                "description": "test-description-2",
                                "status": "IN_PROGRESS"
                            }
                        """));
    }
}