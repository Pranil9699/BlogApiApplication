package com.pranil.blog.app.services;

import com.pranil.blog.app.payloads.PostDto;
import com.pranil.blog.app.payloads.PostResponse;

import java.util.*;

public interface PostService {
	
	
	// create
	
	PostDto createPost(PostDto dto,Integer userId,Integer categoryId);
	
	//update
	
	PostDto updatePost(PostDto dto,Integer postId);
	//delete
	
	void deletePost(Integer postId);
	
	//get single
	
	PostDto getPostById(Integer postId);

	//
	
	//get All 
	PostResponse getAllPost(Integer pageSize,Integer pageNumber,String sortBy,String sortDir);
	
	//get All Post By Category
	PostResponse getPostsByCategory(Integer categoryId,Integer pageSize,Integer pageNumber,String sortBy,String sortDir);
	//get All Post By User
	PostResponse getPostsByUser(Integer userId,Integer pageSize,Integer pageNumber,String sortBy,String sortDir);

	PostResponse searchPosts(String keyword,Integer pageSize,Integer pageNumber,String sortBy,String sortDir);
	
}
