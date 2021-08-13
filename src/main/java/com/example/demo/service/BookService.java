package com.example.demo.service;

import com.example.demo.model.Book;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class BookService {
    /*
        Usually a service implement some business logic and interacts with the repository layer / some external services.
        To simplify things - no repository layer, books will be stored here in the map.
     */

    private Map<Integer, Book> fakeRepository = new HashMap<>();

    public BookService() {
        fakeRepository.put(1, new Book(1, "Yuval Noah Harari", "Sapiens"));
        fakeRepository.put(2, new Book(2, "Yuval Noah Harari", "Homo Deus"));
        fakeRepository.put(3, new Book(3, "Martin Caparros", "El Hambre"));
        fakeRepository.put(4, new Book(4, "Seth Mnookin", "The Panic Virus"));

    }

    public Book create(Book book) {
        var lastId = fakeRepository.size();
        var newBookId = ++lastId;
        book.setId(newBookId);
        fakeRepository.put(newBookId, book);
        return fakeRepository.get(newBookId);
    }

    public Book read(int id) throws NoSuchElementException{
        throwExceptionIfNoElement(id);
        return fakeRepository.get(id);
    }

    public Book update(Book book) throws NoSuchElementException{
        throwExceptionIfNoElement(book.getId());
        fakeRepository.put(book.getId(), book);
        return fakeRepository.get(book.getId());
    }

    public void delete(int id) throws NoSuchElementException{
        throwExceptionIfNoElement(id);
        fakeRepository.remove(id);
    }

    public Map<Integer, Book> getFakeRepository() {
        return fakeRepository;
    }

    private void throwExceptionIfNoElement(int id) {
        if(!fakeRepository.containsKey(id)) {
            throw new NoSuchElementException("No element with id: " + id);
        }
    }
}
