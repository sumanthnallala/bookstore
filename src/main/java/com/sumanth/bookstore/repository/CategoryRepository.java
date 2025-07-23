package com.sumanth.bookstore.repository;

import com.sumanth.bookstore.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(String name);

    void deleteByName(String name);

}
