package com.pranil.blog.app.payloads;

import lombok.*;

@Getter
@Setter
public class CommentDto {

	private int commentId;

	private String content;
	
	private int userId;
}
