package com.pranil.blog.app.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pranil.blog.app.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
