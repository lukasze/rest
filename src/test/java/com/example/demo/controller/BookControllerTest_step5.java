package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/*
    Checking the status code is usually not enough.
    Usually we want to check the returned data.

    One way to do it: jsonPath
    @see https://www.baeldung.com/guide-to-jayway-jsonpath

    For more complex object we map {JSON} <-> Java and then assert object's properties.
    Book is not that complex, but we'll use the second approach.

 */
@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest_step5 {
    @Autowired
    MockMvc mockMvc;
    // helper API to map JAVA <-> {JSON}
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    BookService bookService;


    // read success
    @Test
    // Yet another annotation - to make sure the state of SpringBoot app is the same with every test
    // helpful when we test with in-memory DB.
    @DirtiesContext
    @DisplayName("GET http://localhost:8080/book?id=1 -> HTTP 200, Book in {JSON}")
    public void givenServiceWithFakeRepository_WhenGET_Id1_Then200AndBookReturned() throws Exception {

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/book?id=1")
                        .contentType("application/json")
        )
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        // Get JSON respsonse form MvcResult and use ObjectMapper to map JSON to Java
        String receivedBookInJsonFormat = mvcResult.getResponse().getContentAsString();
        Book receivedBookInJavaFormat = objectMapper.readValue(receivedBookInJsonFormat, Book.class);

        // Make sure the returned object is as expected

        //               expected                               actual
        assertEquals(bookService.getFakeRepository().get(1), receivedBookInJavaFormat);
    }

    // TODO just play with the code (change id to 2, 100; comment out line 55, debug) - how does it affect the test result?
}
