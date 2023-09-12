package com.pranil.blog.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pranil.blog.app.entities.User;
import com.pranil.blog.app.exceptions.ResourceNotFoundException;
import com.pranil.blog.app.repositorys.UserRepo;
@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepo repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = this.repo.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("Username", "not found with email : "+username, 0));
		
		System.out.println(user.getEmail()+" "+user.getPassword());
		return user;
	}

}
