package com.sumanth.bookstore.repository;

import com.sumanth.bookstore.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

  Author findByName(String name);

  void deleteByName(String name);

}
