package com.pranil.blog.app.contollers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pranil.blog.app.config.AppConstants;
import com.pranil.blog.app.payloads.ApiResponse;
import com.pranil.blog.app.payloads.PostDto;
import com.pranil.blog.app.payloads.PostResponse;
import com.pranil.blog.app.services.FileService;
import com.pranil.blog.app.services.PostService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class PostController {
	@Autowired
	private PostService postService;
	@Autowired
	private FileService fileService;
	
	//get the path for image
	@Value("${project.image}")
	private String path;

	// create
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {
//		System.out.println(postDto.toString());
//System.out.println(" user id "+userId+" : category id  "+categoryId);
		PostDto createPost = this.postService.createPost(postDto, userId, categoryId);

		return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);

	}

	// get by user
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<PostResponse> getPostsByUser(@PathVariable Integer userId,
			// we are implimenting the pagination
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
		@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
		@RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir) {

		PostResponse postsByUser = this.postService.getPostsByUser(userId,pageSize, pageNumber,sortBy,sortDir);

		return new ResponseEntity<PostResponse>(postsByUser, HttpStatus.OK);
	}

	// get by category
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<PostResponse> getPostsByCategory(@PathVariable Integer categoryId,
			// we are implimenting the pagination
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir) {


		PostResponse postsByCategory = this.postService.getPostsByCategory(categoryId,pageSize, pageNumber,sortBy,sortDir);
        System.out.println(postsByCategory);
		return new ResponseEntity<PostResponse>(postsByCategory, HttpStatus.OK);
	}

	// get All Posts
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPosts(
			// we are implimenting the pagination
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir) {


		PostResponse AllPosts = this.postService.getAllPost(pageSize, pageNumber,sortBy,sortDir);

		return new ResponseEntity<PostResponse>(AllPosts, HttpStatus.OK);
	}

	// get Post By Id
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {

		PostDto post = this.postService.getPostById(postId);
        
		return new ResponseEntity<PostDto>(post, HttpStatus.OK);
	}

	// delete post
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId) {
		
		this.postService.deletePost(path,postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post Is Deleted!", true), HttpStatus.OK);

	}

	// update post
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto dto, @PathVariable Integer postId) {

		PostDto updatePost = this.postService.updatePost(dto, postId);
		return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);

	}
	
	//searching by title
	@GetMapping("/posts/search/{keyword}")
	public ResponseEntity<PostResponse> searchPostByTitle(
			@PathVariable("keyword") String keyword,
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir
			){
		PostResponse searchPosts = postService.searchPosts(keyword,pageSize, pageNumber,sortBy,sortDir);
		
		return new ResponseEntity<PostResponse>(searchPosts,HttpStatus.OK);
	}

    //post image upload
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(
			@RequestParam("image") MultipartFile file,
			@PathVariable("postId") Integer postId
			) throws IOException{
		PostDto postById = this.postService.getPostById(postId);
		
		String uploadImage = this.fileService.uploadImage(path, file,postById.getImageName());
		
		postById.setImageName(uploadImage);
		
		PostDto post = this.postService.updatePost(postById, postId);
		
		return new ResponseEntity<PostDto>(post,HttpStatus.OK);
	}
	
	// method to server files
	@GetMapping(value="/post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(
			@PathVariable("imageName") String imageName,
			HttpServletResponse response
			) throws IOException
	{
		InputStream resource = this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource,response.getOutputStream());
		
	}
}
