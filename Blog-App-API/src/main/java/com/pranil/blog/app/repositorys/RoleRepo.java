package com.pranil.blog.app.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pranil.blog.app.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {

	}
