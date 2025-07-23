package com.sumanth.bookstore.controller;

import com.sumanth.bookstore.entity.Book;
import com.sumanth.bookstore.service.BooksService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
  public void addBook(@RequestBody Book book) {
    booksService.addNewBookToInventory(book);
  }

  @GetMapping
  public List<Book> fetchBooks() {
    return new ArrayList<>();
  }

  @GetMapping("/{book}")
  public Book fetchBookByName(@PathVariable String book) {
    return new Book();
  }

  @DeleteMapping("/deleteBook/{id}")
  public void deleteBook(@PathVariable Integer id) {

  }

}
