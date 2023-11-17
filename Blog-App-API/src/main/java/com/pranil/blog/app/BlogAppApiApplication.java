package com.pranil.blog.app;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.pranil.blog.app.config.AppConstants;
import com.pranil.blog.app.entities.Role;
import com.pranil.blog.app.repositorys.RoleRepo;

@SpringBootApplication
public class BlogAppApiApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private RoleRepo roleRepo;

	@Override
	public void run(String... args) throws Exception {
		try {
			Role role1 = new Role();
			role1.setId(AppConstants.NORMAL_USER);
			role1.setName("ROLE_NORMAL");

			Role role2 = new Role();
			role2.setId(AppConstants.ADMIN_USER);
			role2.setName("ROLE_ADMIN");

			List<Role> roles = List.of(role1, role2);

			List<Role> saveAll = this.roleRepo.saveAll(roles);

			saveAll.forEach(e -> System.out.println(e.getName()));

		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println(this.encoder.encode("Pranil@2003"));

	}

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApiApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
