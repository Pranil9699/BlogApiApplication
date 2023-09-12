package com.pranil.blog.app.services;

import java.util.List;

import com.pranil.blog.app.payloads.CategoryDto;

public interface CategoryService {

	// create
	public CategoryDto createCategory(CategoryDto categoryDto);

	// update
	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

//delete
	void deleteCategory(Integer categoryId);
	
	//get single
	
	CategoryDto getCategoryDto(Integer categoryId);

	//get All

	List<CategoryDto> getAllCategoryDtos();
}
