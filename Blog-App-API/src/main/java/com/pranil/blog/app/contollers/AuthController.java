package com.pranil.blog.app.contollers;

import java.util.Arrays;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pranil.blog.app.exceptions.ApiException;
import com.pranil.blog.app.payloads.JwtAuthRequest;
import com.pranil.blog.app.payloads.JwtAuthResponse;
import com.pranil.blog.app.payloads.UserDto;
import com.pranil.blog.app.security.JwtTokenHelper;
import com.pranil.blog.app.services.UserService;
import com.pranil.blog.app.services.impl.UserServiceImpl;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {

	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	@Autowired

	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ModelMapper mapper;

	@PostMapping("login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception {

		System.out.println(request.getUsername() + " : " + request.getPassword());

		this.authenticate(request.getUsername(), request.getPassword());

		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());

		String generateToken = this.jwtTokenHelper.generateToken(userDetails);

		JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
		jwtAuthResponse.setToken(generateToken);
		System.out.println((UserDto) this.mapper.map(userDetails, UserDto.class));
		jwtAuthResponse.setUser((UserDto) this.mapper.map(userDetails, UserDto.class));

		return new ResponseEntity<JwtAuthResponse>(jwtAuthResponse, HttpStatus.OK);

	}

	private void authenticate(String username, String password) throws Exception {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				username, password);

		try {
			this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		} catch (BadCredentialsException e) {

			throw new ApiException("Invalid username or password !!");
		}

	}

	@PostMapping("register")
	public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userdto) {
//		char[] charArray = userdto.getPassword().toCharArray();
		System.out.println("Password is : " + userdto.getPassword());
		UserDto registerNewUser = this.userService.registerNewUser(userdto);
//		StringBuilder concatenatedPassword = new StringBuilder();
//		Arrays.asList(charArray).forEach(c -> concatenatedPassword.append(c));

		// Set the concatenated password to the UserDto
//		registerNewUser.setPassword(concatenatedPassword.toString());
		return new ResponseEntity<UserDto>(registerNewUser, HttpStatus.CREATED);
	}

}
