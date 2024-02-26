package com.pranil.blog.app.payloads;

import lombok.Data;

@Data
public class JwtAuthResponse {

	private String token; 
    private UserDto user;
	public JwtAuthResponse() {
		// TODO Auto-generated constructor stub
	}

}
