package com.sumanth.bookstore.controller;

import com.sumanth.bookstore.entity.Book;
import com.sumanth.bookstore.service.BooksService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BooksController {

  @Autowired
  private BooksService booksService;

  @PostMapping
  public ResponseEntity<String> addBook(@RequestBody Book book) {
    booksService.addNewBookToInventory(book);
    return new ResponseEntity<>("Added Book to Inventory", HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<ArrayList<Object>> fetchBooks() {
    return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
  }

  @GetMapping("/{book}")
  public ResponseEntity<Book> fetchBookByName(@PathVariable String book) {
    return new ResponseEntity<>(new Book(), HttpStatus.OK);

  }

  @DeleteMapping("/deleteBook/{id}")
  public ResponseEntity<String> deleteBook(@PathVariable Integer id) {
    return new ResponseEntity<>("Deleted book from inventory", HttpStatus.OK);
  }

}
