package com.pranil.blog.app.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pranil.blog.app.entities.*;
import com.pranil.blog.app.payloads.UserDto;
import com.pranil.blog.app.repositorys.UserRepo;
import com.pranil.blog.app.services.UserService;
import com.pranil.blog.app.exceptions.*;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDto createUser(UserDto user) {
		User usernew = this.dtoToUser(user);
		User save = this.userRepo.save(usernew);
		
		return this.userToDto(save);
	}

	@Override
	public UserDto updateUser(UserDto user, Integer userId) {
		
		User findById = (User) userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User"," id",userId));
		
		findById.setName(user.getName());
		findById.setEmail(user.getEmail());
		findById.setPassword(user.getPassword());
		findById.setAbout(user.getAbout());
		
		User save = this.userRepo.save(findById);
		return this.userToDto(save);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User findById = (User) userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User"," id",userId));

		return this.userToDto(findById);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> userlist=this.userRepo.findAll();
		List<UserDto> list = userlist.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		
		return list;
	}

	@Override
	public void deleteUser(Integer userId) {
		User findById = (User) userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User"," id",userId));
        this.userRepo.delete(findById);

	}
	
	public User dtoToUser(UserDto userDto) {
		User user =this.modelMapper.map(userDto,User.class);
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		return user;
		
	}
	public UserDto userToDto(User user) {
		UserDto userDto =this.modelMapper.map(user,UserDto.class);
//		UserDto.setId(user.getId());
//		UserDto.setName(user.getName());
//		UserDto.setEmail(user.getEmail());
//		UserDto.setPassword(user.getPassword());
//		UserDto.setAbout(user.getAbout());
		return userDto;
	}

}
