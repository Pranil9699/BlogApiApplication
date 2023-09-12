package com.pranil.blog.app.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pranil.blog.app.entities.Category;
import com.pranil.blog.app.exceptions.ResourceNotFoundException;
import com.pranil.blog.app.payloads.CategoryDto;
import com.pranil.blog.app.repositorys.CategoryRepo;
import com.pranil.blog.app.services.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {

		Category category = this.modelMapper.map(categoryDto, Category.class);
		Category save = this.categoryRepo.save(category);

		return this.modelMapper.map(save, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category ", " Category Id ", categoryId));
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());

		return this.modelMapper.map(this.categoryRepo.save(category), CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {

		Category findById = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category ", " Category Id ", categoryId));

		this.categoryRepo.delete(findById);
	}

	@Override
	public CategoryDto getCategoryDto(Integer categoryId) {
		Category findById = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category ", " Category Id ", categoryId));
		return this.modelMapper.map(findById, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategoryDtos() {
		List<Category> findAll = this.categoryRepo.findAll();
		List<CategoryDto> list = findAll.stream().map((category)-> this.modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
		return list;
	}

}
