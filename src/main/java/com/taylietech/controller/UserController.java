package com.taylietech.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import com.taylietech.user.*;
import com.taylietech.exceptions.UserNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;                 


@RestController
public class UserController {
	
	@Autowired
	private UserDAO userdao;

    //an end-point to return all users
	@RequestMapping("/users")
	public List<User> listAllUsers() {
		return userdao.findAll();
	}
	
	
	//an end-point to return a particular user whose id will be passed as a path variable or parameter
	@RequestMapping("/users/{id}")
	public Resource<User> oneUser(@PathVariable int id) {
		User user = userdao.findOne(id);
		if (user == null)
			throw new UserNotFoundException("The requested user does not exist!"); //an exception to be thrown if the particular user id doesn't exist
		
		Resource<User> resource = new Resource<User>(user);
		ControllerLinkBuilder link = linkTo(methodOn(this.getClass()).listAllUsers());
		resource.add(link.withRel("list all users"));
		
		return resource;
	}
	
	
	//an end-point to add a new user
	@PostMapping("users")
	public ResponseEntity<Object> addUser(@Valid @RequestBody User user) {
	   User savedUser = userdao.saveUser(user); //saving a new user
	   URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
	   
	   return ResponseEntity.created(location).build();
	}
	
	
	//an end-point to delete a particular user whose id will be passed as a path variable or parameter
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = userdao.deleteById(id);
		if (user == null)
			throw new UserNotFoundException("The requested user does not exist!"); //an exception to be thrown if the particular user id doesn't exist

	}
	
	
	
}

