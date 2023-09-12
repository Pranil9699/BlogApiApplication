package com.pranil.blog.app.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pranil.blog.app.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
