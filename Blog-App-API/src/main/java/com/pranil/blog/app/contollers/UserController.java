package com.pranil.blog.app.contollers;



import java.util.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pranil.blog.app.payloads.ApiResponse;
import com.pranil.blog.app.payloads.UserDto;
import com.pranil.blog.app.services.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")

public class UserController {

	@Autowired
	private UserService service;

	//create User
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto dto) {
    
		UserDto createUser = this.service.createUser(dto);
		System.out.println(createUser);
		return new ResponseEntity<UserDto>(createUser, HttpStatus.CREATED);
	}
	
	
	// update user
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto dto,@PathVariable("userId") Integer userId){
		
		UserDto updateUser = this.service.updateUser(dto, userId);
		
		return new ResponseEntity<>(updateUser, HttpStatus.CREATED);
	}
	
	// delete user
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser( @PathVariable("userId") Integer userId){
		
		this.service.deleteUser(userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted SuccessFully",true),HttpStatus.OK);
	}
	
	
	// get user
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		return ResponseEntity.ok(this.service.getAllUsers());
	}
	
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSignleUser(@PathVariable("userId") Integer userId){
		return ResponseEntity.ok(this.service.getUserById(userId));
	}
	
	
}
