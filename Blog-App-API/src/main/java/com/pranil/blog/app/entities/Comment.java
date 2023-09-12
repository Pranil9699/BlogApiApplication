package com.pranil.blog.app.entities;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name="comments")
@NoArgsConstructor
@Getter
@Setter
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int commentId;
	
	private String content;
	
	private int userId;
	
	@ManyToOne
	@JoinColumn(name = "post_id")
	private Post post;
}
