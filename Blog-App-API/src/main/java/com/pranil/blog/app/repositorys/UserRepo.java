package com.pranil.blog.app.repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pranil.blog.app.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String email);
}
