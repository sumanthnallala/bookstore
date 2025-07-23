package com.sumanth.bookstore.service;


import com.sumanth.bookstore.entity.Book;

public interface BooksService {

  void addNewBookToInventory(Book book);

  void deleteBook(String book);

  void deleteAuthor(String name);

  void deleteCategory(String name);
}
