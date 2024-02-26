package com.pranil.blog.app.payloads;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
//	@NotBlank(message = "Name Field is Required!")
	@Size(min = 3, max = 20, message = "Name must be between 3 and 20 characters long")
	private String name;
//	@NotBlank(message = "Email Field is Required!")
	@Pattern(regexp = "^[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)*@[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)*$", message = "Please enter a valid email address")
	private String email;
	
	@Pattern(regexp = ".{6,}", message = "Password must be at least 6 characters long")
	private String password;
	@NotEmpty(message = "About must not be empty")
	private String about;
	
	private Set<RoleDto> roles = new HashSet<>();
	
	// error = rawPassword cannot be null
	// at time of signup.js page
	// when we apply the @jsonIgnore for getter method the automatically which ever code comes from front end that automatically ignored
	// so we want to setter method of password and not want the getter method to pass the password back to the user as json object
	// so used getter - jsonignore and used setter - jsonproperty 
	@JsonProperty
	public void setPassword(String password) {
		this.password=password;
	}
	@JsonIgnore
	public String getPassword() {
		return this.password;
	}
}
