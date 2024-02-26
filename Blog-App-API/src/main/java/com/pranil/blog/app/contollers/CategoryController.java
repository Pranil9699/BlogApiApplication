package com.pranil.blog.app.contollers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pranil.blog.app.payloads.ApiResponse;
import com.pranil.blog.app.payloads.CategoryDto;
import com.pranil.blog.app.services.CategoryService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/categories")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	// create
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
//		System.out.println("hi srk");
		CategoryDto createCategory = categoryService.createCategory(categoryDto);
//		System.out.println("Done.");
		return new ResponseEntity<CategoryDto>(createCategory,HttpStatus.CREATED);
	}

	// update

	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable("categoryId") Integer categorId) {
		CategoryDto updateCategory = categoryService.updateCategory(categoryDto,categorId);
		System.out.println("Done.");
		return new ResponseEntity<CategoryDto>(updateCategory,HttpStatus.OK);
	}
	
	// delete

	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("categoryId") Integer categorId) {
		this.categoryService.deleteCategory(categorId);
		System.out.println("Done.");
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category Is Deleted Successfully !!",true),HttpStatus.OK);
	}
	
	// get
	
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable("categoryId") Integer categorId) {
		CategoryDto categoryDto = this.categoryService.getCategoryDto(categorId);
		System.out.println("Done.");
		return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);
	}

	// getAll
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getCategory() {
		System.out.println("Done.");
		return new ResponseEntity<List<CategoryDto>>(this.categoryService.getAllCategoryDtos(),HttpStatus.OK);
	}
}
