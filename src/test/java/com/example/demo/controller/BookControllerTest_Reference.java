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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest_Reference {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    BookService bookService;

    // create success
    @Test
    @DirtiesContext
    @DisplayName("POST http://localhost:8080/book with {JSON} -> HTTP 200, Book in {JSON}")
    public void givenServiceWithFakeRepository_WhenPOST_Then200AndBookReturned() throws Exception {

        Book book = new Book(null, "Jess Zimmerman", "Basic Witches");
        String json = objectMapper.writeValueAsString(book);


        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/book")
                        .contentType("application/json")
                        .content(json)
        )
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String receivedBookInJsonFormat = mvcResult.getResponse().getContentAsString();
        Book receivedBookInJavaFormat = objectMapper.readValue(receivedBookInJsonFormat, Book.class);

        assertEquals(bookService.getFakeRepository().size(), receivedBookInJavaFormat.getId());
        assertEquals(book.getTitle(), receivedBookInJavaFormat.getTitle());
        assertEquals(book.getTitle(), receivedBookInJavaFormat.getTitle());


    }
    // read success
    @Test
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

        String receivedBookInJsonFormat = mvcResult.getResponse().getContentAsString();
        Book receivedBookInJavaFormat = objectMapper.readValue(receivedBookInJsonFormat, Book.class);

        //               expected                               actual
        assertEquals(bookService.getFakeRepository().get(1), receivedBookInJavaFormat);

    }
    // read fail
    @Test
    @DirtiesContext
    public void givenServiceWithFakeRepository_WhenGETWithNonExistingId_Then400Returned() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.get("/book?id=100")
                        .contentType("application/json")
        )
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }
    // update success
    @Test
    @DirtiesContext
    @DisplayName("PUT http://localhost:8080/book with {JSON} -> HTTP 200, updated Book in {JSON}")
    public void givenServiceWithFakeRepository_WhenPUT_ThenUpdatedBookReturned() throws Exception {

        Book book = new Book(1, "Jess Zimmerman_updated", "Basic Witches_updated");
        String json = objectMapper.writeValueAsString(book);

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.put("/book")
                        .contentType("application/json")
                        .content(json)
        )
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String receivedBookInJsonFormat = mvcResult.getResponse().getContentAsString();
        Book receivedBookInJavaFormat = objectMapper.readValue(receivedBookInJsonFormat, Book.class);

        assertEquals(book, receivedBookInJavaFormat);
    }

    // update fail
    @Test
    @DirtiesContext
    public void givenServiceWithFakeRepository_WhenPUTWithNonExistingId_Then400Returned() throws Exception {

        Book book = new Book(100, "Jess Zimmerman_updated", "Basic Witches_updated");
        String json = objectMapper.writeValueAsString(book);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/book")
                        .contentType("application/json")
                        .content(json)
        )
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());

    }

    @Test
    @DirtiesContext
    @DisplayName("DELETE http://localhost:8080/book with {JSON} -> HTTP 200, deleted Book from fakeRepository")
    public void givenServiceWithFakeRepository_WhenDELETE_Then200Returned() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/book")
                        .contentType("application/json")
                        .param("id","1")
        )
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        assertFalse(bookService.getFakeRepository().containsKey(1));
    }

    @Test
    @DirtiesContext
    public void givenServiceWithFakeRepository_WhenDELETEWithNonExistingId_Then400ReturnedAndFakeRepositorySizeStaysTheSame() throws Exception {

        var initialRepoSize = bookService.getFakeRepository().size();

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/book")
                        .contentType("application/json")
                        .param("name","100")
        )
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());

        var repoSizeAfterDELETERequest = bookService.getFakeRepository().size();

        assertEquals(initialRepoSize, repoSizeAfterDELETERequest);
    }

}