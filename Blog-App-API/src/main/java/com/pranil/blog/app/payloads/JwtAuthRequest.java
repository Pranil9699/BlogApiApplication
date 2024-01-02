package com.pranil.blog.app.payloads;

import lombok.Data;

@Data
public class JwtAuthRequest {

	//our username actually a emailId
	private String username;
	private String password;
	
	
	public JwtAuthRequest() {
		// TODO Auto-generated constructor stub
	}

}
