package com.pranil.blog.app.payloads;

import org.hibernate.validator.constraints.Length;

import lombok.*;

@Getter
@Setter
public class CommentDto {

	private int commentId;

	@Length(max = 255)
	private String content;
	
	private int userId;
	private int postId;
}
