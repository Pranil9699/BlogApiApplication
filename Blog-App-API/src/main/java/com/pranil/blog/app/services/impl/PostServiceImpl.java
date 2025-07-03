package com.pranil.blog.app.services.impl;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.hibernate.query.criteria.internal.expression.function.SubstringFunction;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.pranil.blog.app.entities.*;
import com.pranil.blog.app.exceptions.ResourceNotFoundException;
import com.pranil.blog.app.payloads.PostDto;
import com.pranil.blog.app.payloads.PostResponse;
import com.pranil.blog.app.repositorys.CategoryRepo;
import com.pranil.blog.app.repositorys.PostRepo;
import com.pranil.blog.app.repositorys.UserRepo;
import com.pranil.blog.app.services.PostService;

@Service
public class PostServiceImpl implements PostService {
	@Autowired
	private PostRepo postRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDto createPost(PostDto dto, Integer userId, Integer categoryId) {
		System.out.println(userId + "  : " + categoryId);
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User ", " not found ", userId));
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category ", " not found ", categoryId));

		Post post = this.modelMapper.map(dto, Post.class);
		post.setImageName("default.png");
		post.setAddDate(new Date());
		post.setUser(user);
		post.setCategory(category);

		Post save = this.postRepo.save(post);
		System.out.println(save);
		return this.modelMapper.map(save, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto dto, Integer postId) {

		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "not found", postId));

		post.setTitle(dto.getTitle());
		post.setContent(dto.getContent());
		if (dto.getImageName() != null)
			post.setImageName(dto.getImageName());
        Category category = this.categoryRepo.findById(dto.getCategory().getCategoryId()).get();
//        System.out.println(category.toString());
		post.setCategory(category);
		Post save = this.postRepo.save(post);

		return this.modelMapper.map(save, PostDto.class);
	}

	@Override
	public void deletePost(String path, Integer postId) {

        Post post = this.postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException(" Post ", " not found ", postId));

        this.postRepo.deleteById(postId);

        File file2 = new File(path + File.separator + post.getImageName());
        System.out.println(file2.getName());
        if (file2.exists()){
            System.out.println("File Deleted");
        System.out.println(file2.delete());
    }


	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException(" Post ", " not found ", postId));

		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostResponse getAllPost(Integer pageSize, Integer pageNumber, String sortBy, String sortDir) {

		Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

		Pageable of = PageRequest.of(pageNumber, pageSize, sort);

		Page<Post> pagePost = this.postRepo.findAll(of);
		List<Post> content = pagePost.getContent();

		List<PostDto> collect = content.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return this.getObject(collect, pagePost, sortBy, sortDir);
	}

	// comman method for the return the object of PostResponse
	public PostResponse getObject(List<PostDto> collect, Page<Post> pagePost, String sortBy, String sortDir) {
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(collect);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElement(pagePost.getTotalElements());
		postResponse.setTotalpages(pagePost.getTotalPages());
		postResponse.setFirstPage(pagePost.isFirst());
		postResponse.setLastPage(pagePost.isLast());
		postResponse.setSortBy(sortBy);
		postResponse.setSortDir(sortDir);
		return postResponse;
	}

	@Override
	public PostResponse getPostsByCategory(Integer categoryId, Integer pageSize, Integer pageNumber, String sortBy,
			String sortDir) {

		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException(" Category ", " not Found ", categoryId));

		Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

		Pageable of = PageRequest.of(pageNumber, pageSize, sort);

		Page<Post> pagePost = this.postRepo.findByCategory(category, of);

		// List<Post> posts = this.postRepo.findByCategory(category);

		List<PostDto> collect = pagePost.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return this.getObject(collect, pagePost, sortBy, sortDir);

	}

	@Override
	public PostResponse getPostsByUser(Integer userId, Integer pageSize, Integer pageNumber, String sortBy,
			String sortDir) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException(" User ", " not Found ", userId));

		Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

		Pageable of = PageRequest.of(pageNumber, pageSize, sort);

		Page<Post> pagePost = this.postRepo.findByUser(user, of);

		// List<Post> posts = this.postRepo.);

		List<PostDto> collect = pagePost.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return this.getObject(collect, pagePost, sortBy, sortDir);

	}

	@Override
	public PostResponse searchPosts(String keyword, Integer pageSize, Integer pageNumber, String sortBy,
			String sortDir) {

		Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

		Pageable of = PageRequest.of(pageNumber, pageSize, sort);

		Page<Post> pagePost = this.postRepo.findByTitleContaining(keyword, of);

		List<PostDto> collect = pagePost.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return this.getObject(collect, pagePost, sortBy, sortDir);

	}

}
