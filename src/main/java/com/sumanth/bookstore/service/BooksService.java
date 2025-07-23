package com.sumanth.bookstore.service;


import com.sumanth.bookstore.entity.Book;
import java.util.List;

public interface BooksService {

  void addNewBookToInventory(Book book);

  Book fetchBookByTitle(String title);

  List<Book> fetchAllBooks();

  void deleteBookByTitle(String title);

  void deleteAuthor(String name);

  void deleteCategory(String name);
}
