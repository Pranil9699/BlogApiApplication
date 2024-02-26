package com.pranil.blog.app.entities;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.*;
import javax.validation.constraints.Email;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//@ToString
public class User implements UserDetails{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "user_name", nullable = false, length = 100)
	
	private String name;
	
	@Column(unique = true,nullable = false)
//	@Email(regexp = )
	private String email;
	@Column(nullable = false)
	private String password;
	private String about;

//	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	private List<Post> posts = new ArrayList<>();
	// look here i chnaged here to LAZY because at time post deletion it didnt done soo, must check 
	
	/*
	 * 
	 * Changing the fetch type from FetchType.EAGER to FetchType.LAZY can affect the behavior of your application, especially when dealing with relationships between entities. Let's understand the implications:

EAGER Fetch:

In EAGER fetching, associated entities (in this case, posts) are loaded immediately when the owning entity (User) is loaded.
This means that when you retrieve a User entity, all associated posts are also loaded into memory.
If you have a large number of posts or if the associated entities have a significant amount of data, it could lead to performance issues as all the data is fetched eagerly.
LAZY Fetch:

In LAZY fetching, associated entities are loaded only when they are explicitly accessed or requested.
In your case, when the fetch type is changed to LAZY, the posts associated with a user are not loaded immediately when the user is loaded.
When you access the posts property of a User (e.g., by calling user.getPosts()), the posts are then loaded from the database.
Now, regarding the impact on post deletion:

When you delete a post, and the fetch type is EAGER, Hibernate will have loaded all posts associated with a user. If you then try to access the posts after one has been deleted, Hibernate may not realize that a post has been removed, leading to potential issues.

When the fetch type is LAZY, posts are not loaded unless explicitly requested. So, if you delete a post, and then access the posts associated with a user, Hibernate will fetch the posts from the database at that moment. This ensures that you have the most up-to-date data.

In summary, changing the fetch type to LAZY may have resolved the issue by preventing Hibernate from prematurely loading all posts associated with a user, allowing for more accurate post deletion and retrieval. However, it's important to be aware of the potential performance implications of lazy loading, especially in scenarios where you frequently access the associated entities. Consider your application's specific use cases and performance requirements when deciding on the fetch type.
	 */
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Post> posts = new ArrayList<>();

	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role", referencedColumnName = "id"))
	private Set<Role> roles = new HashSet<>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		System.out.println("me");
		List<SimpleGrantedAuthority> collect = this.roles.stream().map((role)-> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

		collect.forEach((one)->System.out.println(one.getAuthority()+" "));
		System.out.println("me");

		return collect;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
