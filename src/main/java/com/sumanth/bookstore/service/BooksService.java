package com.sumanth.bookstore.service;


import com.sumanth.bookstore.entity.Book;
import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface BooksService {

  void addNewBookToInventory(Book book, MultipartFile file) throws IOException;

  Book fetchBookByTitle(String title);

  List<Book> fetchAllBooks();

  void deleteBookByTitle(String title);

  void deleteAuthor(String name);

  void deleteCategory(String name);
}
