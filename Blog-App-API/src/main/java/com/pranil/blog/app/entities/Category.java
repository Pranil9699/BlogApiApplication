package com.pranil.blog.app.entities;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="categories")
@NoArgsConstructor
@Getter
@Setter

public class Category {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer categoryId;
	
	@Column(length = 100, nullable = false,name="title")
	private String categoryTitle;
	
	@Column(name="description")
	private String categoryDescription;
	
	@OneToMany(mappedBy = "category",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Post> posts=new ArrayList<>();
}
