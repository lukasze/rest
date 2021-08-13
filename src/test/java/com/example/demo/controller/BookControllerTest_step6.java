package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/*
    Sometimes, while testing, we don't want /need to have a full Spring app - just it's parts.

    E.g. instead @SpringBootTest - @WebMvcTest.
    It will tell Spring - "I'm interested only in the controller part).

    Here is a catch - in our app we cannot test an endpoint method, without BookService (the controller methods
    use BookService methods).

    @MockBean to the rescue - we ask Spring for a mock of BookServicew - see the comments below)

 */
@WebMvcTest
public class BookControllerTest_step6 {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    /*
        It's not a 'regular' bean - we'll have to program this object methods' behavior.

        We use @MockBean either to have an extra bean (that wouldn't be created based on the Spring configuration for
        our test class), or to replace an existing bean.
     */

    @MockBean
    BookService bookService;


    // read success with a mocked response
    @Test
    @DirtiesContext
    @DisplayName("GET http://localhost:8080/book?id=1 -> HTTP 200, Book in {JSON}")
    public void givenServiceWithFakeRepository_WhenGET_Id1_Then200AndBookReturned() throws Exception {
        Book mockedBook = new Book(1, "Your favourite author","Your favourite book");

        // TODO 1 run the test
        // TODO 2 change bookService.read(1)  bookService.read(2), rerun the test and see what happened :)
        Mockito.when(bookService.read(1)).thenReturn(mockedBook);

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/book?id=1")
                        .contentType("application/json")
        )
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        // Get JSON repsonse form MvcResult and use ObjectMapper to map JSON to Java
        String receivedBookInJsonFormat = mvcResult.getResponse().getContentAsString();
        Book receivedBookInJavaFormat = objectMapper.readValue(receivedBookInJsonFormat, Book.class);

        // Make sure the returned object is as expected

        //               expected                               actual
        assertEquals(mockedBook, receivedBookInJavaFormat);
    }

    /*
        TODO 3 (extra) write your own test with a mocked response - use BookControllerTest_Reference or google
            for some more adventurous tests :).


        We haven't covered all the possible options, but that's more than śenough to make you comfortable with the test!
     */
}