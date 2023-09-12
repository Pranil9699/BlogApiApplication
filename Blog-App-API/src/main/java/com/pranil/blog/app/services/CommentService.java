package com.pranil.blog.app.services;

import com.pranil.blog.app.payloads.CommentDto;

public interface CommentService {

	CommentDto createComment(CommentDto dto,Integer postId, Integer userId);
	
	void deleteComment(Integer commentId);
	
}
