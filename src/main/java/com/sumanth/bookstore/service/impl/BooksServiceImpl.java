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
    Author author = authorRepository.findByName(book.getAuthor().getName());
    if (author == null) {
      Author author1 = new Author();
      author1.setName(book.getAuthor().getName());
      authorRepository.save(author1);
    }

    Category category = categoryRepository.findByName(book.getCategory().getName());
    if (category == null) {
      Category category1 = new Category();
      category1.setName(book.getCategory().getName());
      categoryRepository.save(category1);
    }

    validateBook(book);
    Book savedBook = bookRepository.save(book);
    logger.info(String.valueOf(book));
  }

  @Override
  public void deleteBook(String book) {
    Book bookToDelete = bookRepository.findByTitle(book);
    if (bookToDelete != null) {
      bookRepository.delete(bookToDelete);
      logger.info("Deleted book: " + book);
    } else {
      logger.warn("Book not found: " + book);
    }
  }

  @Override
  public void deleteAuthor(String name) {
    Author author = authorRepository.findByName(name);
    if (author != null) {
      authorRepository.deleteByName(name);
      logger.info("Deleted author: " + name);
    } else {
      logger.warn("Author not found: " + name);
    }
  }

  @Override
  public void deleteCategory(String name) {
    Category category = categoryRepository.findByName(name);
    if (category != null) {
      categoryRepository.deleteByName(name);
      logger.info("Deleted category: " + name);
    } else {
      logger.warn("Category not found: " + name);
    }
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
