package com.example.demo.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/*
    Spring Boot tests - much more than JUnit tests. We can test the whole Spring Boot application, it's parts, test the
    whole application with some parts replaced etc.

    A lot of options + some syntax you need to get accustomed to - takes a while, don't get discouraged!

    To make a long story short... :)
 */



// here  we configure Spring (e.g. to test the app as a whole, endpoints layer, some services, repositories etc.)
public class BookControllerTest_step1 {

    /*
            Here we declare some types (APIs) that will help us with the tests, most common:
            MockMvc, ObjectMapper.

            We ask Spring to provide us with their instances - @Autowired

     */

    /*
        Here we ask Spring to give us any of it's beans if needed.
        We can use an 'original' bean using @Autowired or replace it with @MockBean

        As a side note - a Spring Bean is a java object created and managed by Spring Container.
     */

    /*
            TODO run the test class and check the logs
            How come we don't have the main method and the program is running?
     */

    @Test
    void justARegularJunitTest() {
        // Unit tests -  It's a good practice to use fail() not to confuse yourself / your teammates
        fail();
    }
}
