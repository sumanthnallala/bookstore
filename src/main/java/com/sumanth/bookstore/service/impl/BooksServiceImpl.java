package com.sumanth.bookstore.service.impl;

import com.sumanth.bookstore.entity.Author;
import com.sumanth.bookstore.entity.Book;
import com.sumanth.bookstore.entity.Category;
import com.sumanth.bookstore.repository.AuthorRepository;
import com.sumanth.bookstore.repository.BooksRepository;
import com.sumanth.bookstore.repository.CategoryRepository;
import com.sumanth.bookstore.service.BooksService;
import com.sumanth.bookstore.service.S3Service;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class BooksServiceImpl implements BooksService {

  private static final Logger logger = LoggerFactory.getLogger(BooksService.class);

  @Autowired
  private BooksRepository bookRepository;

  @Autowired
  private AuthorRepository authorRepository;

  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  private S3Service s3Service;

  public void addNewBookToInventory(Book book, MultipartFile file) throws IOException {
    String s3Key = s3Service.uploadFile(file);
    Author author = authorRepository.findByName(book.getAuthor().getName());
    if (author == null) {
      author = new Author();
      author.setName(book.getAuthor().getName());
      authorRepository.save(author);
    }
    book.setAuthor(author);

    Category category = categoryRepository.findByName(book.getCategory().getName());
    if (category == null) {
      category = new Category();
      category.setName(book.getCategory().getName());
      categoryRepository.save(category);
    }
    book.setCategory(category);

    validateBook(book);
    book.setS3Key(s3Key);
    bookRepository.save(book);
    logger.info(String.valueOf(book));
  }

  @Override
  public Book fetchBookByTitle(String title) {
    Book foundBook = bookRepository.findByTitle(title);
    if (foundBook == null) {
      logger.warn("Book not found: " + title);
      return null;
    }
    return foundBook;
  }

  @Override
  public void deleteBookByTitle(String title) {
    Book bookToDelete = bookRepository.findByTitle(title);
    if (bookToDelete != null) {
      bookRepository.delete(bookToDelete);
      logger.info("Deleted book: " + title);
    } else {
      logger.warn("Book not found: " + title);
    }
  }

  @Override
  public void deleteAuthor(String name) {
    Author author = authorRepository.findByName(name);
    if (author != null) {
      authorRepository.deleteById(author.getId());
      logger.info("Deleted author: " + name);
    } else {
      logger.warn("Author not found: " + name);
    }
  }

  @Override
  public void deleteCategory(String name) {
    Category category = categoryRepository.findByName(name);
    if (category != null) {
      categoryRepository.deleteById(category.getId());
      logger.info("Deleted category: " + name);
    } else {
      logger.warn("Category not found: " + name);
    }
  }

  public List<Book> fetchAllBooks() {
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
