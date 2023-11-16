package com.pranil.blog.app.payloads;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.*;

import com.pranil.blog.app.entities.Role;

////import javax.validation.constraints.NotNull;
//import org.hibernate.validator.constraints.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDto {

	private int id;
	@NotBlank(message = "Name Field is Required!")
	@Size(min = 3, max = 20, message = "Name must be between 3 and 20 characters long")
	private String name;
	@NotBlank(message = "Email Field is Required!")
	@Pattern(regexp = "^[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)*@[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)*$", message = "Please enter a valid email address")
	private String email;
	@NotEmpty
	@Pattern(regexp = ".{6,}", message = "Password must be at least 6 characters long")
	private String password;
	@NotEmpty
	private String about;
	
	private Set<RoleDto> roles = new HashSet<>();
}
