package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BookControllerTest_step3 {

    // Let's grab one of the spring beans and test its behavior: BookService
    @Autowired
    BookService bookService;

    @Test
    void createSuccess() {
        // given
        var initialFakeRepoSize = bookService.getFakeRepository().size();
        Book aNewBookToSave = new Book(null, "Peter Wohlleben","The Hidden Life of Trees");

        // when
        Book savedBook = bookService.create(aNewBookToSave);
        
        // then
        var fakeRepoSizeAfterCreate = bookService.getFakeRepository().size();
        assertEquals((initialFakeRepoSize + 1), bookService.getFakeRepository().size());

    }

    // TODO 1 implement a test for read method - success

    @Test
    // A nice feature in JUnit5 - @DisplayName gives us a lot of flexibility regarding our tests description
    @DisplayName(" I can write here whatever I want! （￣ー￣）")
    void readSuccess() {
        fail();
    }

    /*
            TODO 2 (extra) - try to implement a test for read, that an exception is thrown
            This is a JUnit5 feature but may come in handy in Spring tests as well
            @see https://howtodoinjava.com/junit5/expected-exception-example/
     */
}
