package com.pranil.blog.app.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pranil.blog.app.entities.*;

import com.pranil.blog.app.exceptions.ResourceNotFoundException;
import com.pranil.blog.app.payloads.*;
import com.pranil.blog.app.repositorys.*;

import com.pranil.blog.app.services.*;
@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private PostRepo postRepo;

	@Autowired
	private CommentRepo commentRepo;

	@Autowired
	private ModelMapper mapper;

	@Override
	public CommentDto createComment(CommentDto dto, Integer postId,Integer userId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "not found", userId));
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "not found", postId));

		Comment comment = this.mapper.map(dto, Comment.class);
		comment.setUserId(userId);
		comment.setPost(post);
		Comment save = commentRepo.save(comment);

		return this.mapper.map(save, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
	
//		System.out.println("hi");
		Comment orElseThrow = this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","not found", commentId));
		this.commentRepo.delete(orElseThrow);
//		Comment previousComment=this.commentRepo.findById(commentId).get();
//		System.out.println(previousComment);
		

	}

}
