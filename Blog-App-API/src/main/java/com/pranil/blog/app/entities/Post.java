package com.pranil.blog.app.entities;

import java.util.Date;
import java.util.*;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "post")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postId;
	@Column(name = "post_title", length = 255, nullable = false)
	private String title;
//	@Column(length = 10000)
	private String content;
	private String imageName;
	private Date addDate;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	@OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
	private Set<Comment> comment = new HashSet<>();
}
