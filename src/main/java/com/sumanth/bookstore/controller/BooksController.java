package com.sumanth.bookstore.controller;

import com.sumanth.bookstore.entity.Book;
import com.sumanth.bookstore.service.BooksService;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/books")
public class BooksController {

  @Autowired
  private BooksService booksService;

  @PostMapping(consumes = "multipart/form-data")
  public ResponseEntity<String> addBook(@RequestPart("data") Book book,
      @RequestPart("file") MultipartFile file) throws IOException {
    booksService.addNewBookToInventory(book, file);
    return new ResponseEntity<>("Added Book to Inventory", HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<Book>> fetchAllBooks() {
    List<Book> books = booksService.fetchAllBooks();
    return new ResponseEntity<>(books, HttpStatus.OK);
  }

  @GetMapping("/{title}")
  public ResponseEntity<Book> fetchBookByTitle(@PathVariable String title) {
    return new ResponseEntity<>(booksService.fetchBookByTitle(title), HttpStatus.OK);
  }

  @DeleteMapping("/deleteBook/{title}")
  public ResponseEntity<String> deleteBookByTitle(@PathVariable String title) {
    booksService.deleteBookByTitle(title);
    return new ResponseEntity<>("Deleted book from inventory", HttpStatus.OK);
  }

  @DeleteMapping("/deleteAuthor/{name}")
  public ResponseEntity<String> deleteAuthor(@PathVariable String name) {
    booksService.deleteAuthor(name);
    return new ResponseEntity<>("Deleted author from inventory", HttpStatus.OK);
  }

  @DeleteMapping("/deleteCategory/{name}")
  public ResponseEntity<String> deleteCategory(@PathVariable String name) {
    try {
      booksService.deleteCategory(name);
      return new ResponseEntity<>("Deleted category from inventory", HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
  }

}
