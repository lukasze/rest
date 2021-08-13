package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
public class BookController {

	@Autowired
	private BookService bookService;

	@PostMapping("/book")
	public Book create(@RequestBody Book book) {
		return bookService.create(book);
	}

	@GetMapping("/book")
	public Book read(@RequestParam int id) {
		return bookService.read(id);
	}

	@PutMapping("/book")
	public Book update(@RequestBody Book book) {
		return bookService.update(book);
	}

	@DeleteMapping("/book")
	public void delete(@RequestParam int id) {
		bookService.delete(id);
	}

	// Convert an exception to an HTTP status code
	@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="No element with given id")
	@ExceptionHandler(NoSuchElementException.class)
	public void noSuchElement() {
		// The job is done with annotations only :)
	}


}