package com.pranil.blog.app.entities;

import javax.annotation.processing.Generated;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter

public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;

}
