package com.sumanth.bookstore.service.impl;

import com.sumanth.bookstore.entity.Book;
import com.sumanth.bookstore.repository.BooksRepository;
import com.sumanth.bookstore.service.BooksService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BooksServiceImpl implements BooksService {

  private static final Logger logger = LoggerFactory.getLogger(BooksService.class);

  @Autowired
  private BooksRepository bookRepository;

  public void addNewBookToInventory(Book book) {
    Book savedBook = bookRepository.save(book);
    logger.info(String.valueOf(book));
  }

  public List<Book> fetchBooks() {
    List<Book> books = bookRepository.findAll();
    return books;
  }

  public void deleteNewBookFromInventory(Integer id) {
    bookRepository.deleteById(id);
  }
}
