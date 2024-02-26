package com.pranil.blog.app.payloads;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class CategoryDto {

	private Integer categoryId;
//	@NotEmpty(message="Title Must Fill!")
	@Size(min=4,max = 50)
	private String categoryTitle;
//	@NotEmpty(message="Description Must Fill!")
	@Size(min=10,max = 1000)
	private String categoryDescription;
}
