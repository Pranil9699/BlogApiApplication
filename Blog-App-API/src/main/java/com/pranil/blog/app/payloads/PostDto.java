package com.pranil.blog.app.payloads;

import java.util.*;

import org.hibernate.validator.constraints.Length;

import com.pranil.blog.app.entities.*;
import lombok.*;


@NoArgsConstructor
@Getter
@Setter
@ToString
public class PostDto {
	
	private Integer postId;
	
	private String title;
//	@Length(max=10000)
	private String content;
	
	private String imageName;
	
	private Date addDate;
	
	private CategoryDto category;
	
	private UserDto user;
	
	private Set<CommentDto> comment = new HashSet<>();
	
}
