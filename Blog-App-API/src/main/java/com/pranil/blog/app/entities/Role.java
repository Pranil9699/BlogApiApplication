package com.pranil.blog.app.entities;

import javax.annotation.processing.Generated;
import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@Data

public class Role {

	@Id
	private int id;
	private String name;

}
