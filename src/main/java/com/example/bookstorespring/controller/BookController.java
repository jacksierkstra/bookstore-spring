package com.example.bookstorespring.controller;

import com.example.bookstorespring.model.Book;
import com.example.bookstorespring.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController("/api/books")
public class BookController {

    private BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Book>> getBooks(Pageable pageable) {
        return ResponseEntity.ok(this.bookRepository.findAll(pageable));
    }

    @RequestMapping(value = "/{bookId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> getBook(@PathVariable("bookId") Long bookId) {
        return ResponseEntity.of(this.bookRepository.findById(bookId));
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> addBook(Book book) {
        Book createdBook = this.bookRepository.save(book);
        return ResponseEntity.created(URI.create(String.format("/api/books/%s", createdBook.id))).build();
    }

    @RequestMapping(value = "/{bookId}", method = RequestMethod.DELETE)
    public void deleteBook(@PathVariable("bookId") Long bookId) {
        this.bookRepository.deleteById(bookId);
    }
}
