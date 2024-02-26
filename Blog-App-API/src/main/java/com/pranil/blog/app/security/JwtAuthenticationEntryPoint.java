package com.pranil.blog.app.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	public JwtAuthenticationEntryPoint() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
//		System.out.println(authException.toString());
//		String name = SecurityContextHolder.getContext().getAuthentication().getName();
//		System.out.println("Name :"+name);
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Access Denined !!");

	}

}
