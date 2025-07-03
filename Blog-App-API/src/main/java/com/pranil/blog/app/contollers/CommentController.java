package com.pranil.blog.app.contollers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pranil.blog.app.payloads.ApiResponse;
import com.pranil.blog.app.payloads.CommentDto;
import com.pranil.blog.app.services.CommentService;

@RestController
@RequestMapping("/api/v1")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@PostMapping("/post/{postId}/user/{userId}/comments")
	public ResponseEntity<CommentDto> createComment(@Valid @RequestBody CommentDto dto, @PathVariable Integer postId,@PathVariable Integer userId) {

		CommentDto createComment = this.commentService.createComment(dto, postId,userId);
        System.out.println("Comming cooment");
		return new ResponseEntity<CommentDto>(createComment, HttpStatus.CREATED);

	}
	
	@DeleteMapping("/post/{commentId}/comments")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId) {
		
		this.commentService.deleteComment(commentId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("Comment is Deleted!",true), HttpStatus.OK);
		
	}

}
