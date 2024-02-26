package com.pranil.blog.app.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DuplicateEmailException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String email;

	public DuplicateEmailException(String email) {
		super(String.format("This email Id - %s , Already Taken Another User", email));
		this.email = email;
	}
}