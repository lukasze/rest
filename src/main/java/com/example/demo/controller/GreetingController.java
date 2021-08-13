package com.example.demo.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Greeting;
import com.example.demo.repository.GreetingRepository;

// add some superpowers: http client <-> endpoint methods in this class
@RestController
public class GreetingController {
	
	/*
	 *  Use the main Spring feature - Dependency Injection
	 *  
	 *  Spring will inject an implementaiton of GreetingRepository
	 *  (it will create an object of a class implementing GreetingRepository).
	 *  How? It uses spring data project to do so.
	 */
	
	@Autowired
	private GreetingRepository greetingRepository;
	
	// http://localhost:8080/greeting?name=noname
	@GetMapping("/greeting")
	public Greeting greet(@RequestParam(required = false, defaultValue = "World")
							String name) {
		String greetingMsg = "Hello, " + name + "!";
		if(name.equals("World")) {
			name = "Default: World";
		}
	
		Greeting aNewGreetingToBeSaved = new Greeting(name, greetingMsg, LocalDateTime.now());
	
		Greeting objectFromRDBMTable = greetingRepository.save(aNewGreetingToBeSaved);
		
		return objectFromRDBMTable;
	}
	

}
