package com.sumanth.bookstore.service.impl;

import com.sumanth.bookstore.entity.Author;
import com.sumanth.bookstore.entity.Book;
import com.sumanth.bookstore.entity.Category;
import com.sumanth.bookstore.repository.AuthorRepository;
import com.sumanth.bookstore.repository.BooksRepository;
import com.sumanth.bookstore.repository.CategoryRepository;
import com.sumanth.bookstore.service.BooksService;
import java.math.BigDecimal;
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

  @Autowired
  private AuthorRepository authorRepository;

  @Autowired
  private CategoryRepository categoryRepository;

  public void addNewBookToInventory(Book book) {
    Author author = new Author();
    author.setName(book.getAuthor().getName());
    authorRepository.save(author);

    Category category = new Category();
    category.setName(book.getCategory().getName());
    categoryRepository.save(category);

    validateBook(book);
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

  private void validateBook(Book book) {
    if (book == null) {
      throw new IllegalArgumentException("Book cannot be null");
    }
    if (book.getTitle() == null || book.getTitle().isEmpty()) {
      throw new IllegalArgumentException("Book title cannot be null or empty");
    }
    if (book.getPrice() == null || book.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
      throw new IllegalArgumentException("Book price must be greater than zero");
    }
    if (book.getReleaseDate() == null) {
      throw new IllegalArgumentException("Book release date cannot be null");
    }
    if (book.getQuantity() == null || book.getQuantity() <= 0) {
      throw new IllegalArgumentException("Book quantity must be greater than zero");
    }
    if (book.getAuthor() == null) {
      throw new IllegalArgumentException("Book author cannot be null");
    }
    if (book.getCategory() == null) {
      throw new IllegalArgumentException("Book category cannot be null");
    }
  }
}
