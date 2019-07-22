package com.taylietech.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="Useful info about the user")
@Entity
public class User {
	
	protected User() {}; //Default Constructor
	
	@Id
	@GeneratedValue
    private int id;
	
    @ApiModelProperty(notes="Name should be at least 3 characters long")
    @Size(min=3, message="Name should have at least 3 characters")
	private String name;
	
    @ApiModelProperty(notes="The date must be in the past")
    @Past
	private Date dateOfBirth;
    
    @OneToMany(mappedBy="user")
    private List<Post> posts;

	public User(int id, String name, Date dateOfBirth) {
		this.id = id;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public List<Post> getPost() {
		return posts;
	}

	public void setPost(List<Post> post) {
		this.posts = post;
	}

	@Override
	public String toString() {
		return "UserDAO [id=" + id + ", name=" + name + ", dateOfBirth=" + dateOfBirth + "]";
	}


}
