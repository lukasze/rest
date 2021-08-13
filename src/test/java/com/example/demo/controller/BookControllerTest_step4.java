package com.example.demo.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/*
    So far we've tested one of the Spring Beans - now it's time to test one of it's endpoint.
    We'll use a helper class - MockMvc, Spring will give us it's instance.

 */
@SpringBootTest
// Without @AutoConfigureMockMvc Spring won;t be able to @Autowired MockMvc
@AutoConfigureMockMvc
public class BookControllerTest_step4 {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("GET http://localhost:8080/book?id=1 -> HTTP 200, Book in {JSON}")
    // yet another convention: given/ when / then in the name of test method
    public void givenServiceWithFakeRepository_WhenGET_Id1_Then200AndBookReturned() throws Exception {
       // use MockMvc to test the endpoint
       mockMvc.perform(
                // use HTTP GET, call /book?id=1 - notice we don't use the full url here (http://localhost:800/book?id=1)
                MockMvcRequestBuilders.get("/book?id=1")
                        .contentType("application/json")
        )
               // add some logging
                .andDo(print())
               // expect 200
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    // TODO write a test that will return HTTP 400 (send non-existing id)

}
