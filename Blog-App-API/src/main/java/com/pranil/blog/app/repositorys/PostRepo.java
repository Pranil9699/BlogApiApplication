package com.pranil.blog.app.repositorys;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.pranil.blog.app.entities.Category;
import com.pranil.blog.app.entities.Post;
import com.pranil.blog.app.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer>{

	
	Page<Post> findByUser(User user, Pageable of);
//	List<Post> findByCategory(Category Category);
	Page<Post> findByCategory(Category category, Pageable of);

	// for finding post by their Title 
	Page<Post> findByTitleContaining(String title, Pageable of);
}
