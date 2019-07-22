package com.taylietech.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.taylietech.exceptions.UserNotFoundException;
import com.taylietech.models.Post;
import com.taylietech.models.User;
import com.taylietech.repositories.UserRepository;

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
	private UserRepository userRepository;

	// an end-point to return all users
	@RequestMapping("/jpa/users")
	public List<User> listAllUsers() {
		return userRepository.findAll();
	}

	// an end-point to return post for a specific user
	@RequestMapping("/jpa/user/{id}/post")
	public List<Post> userPost(@PathVariable int id) {
		Optional<User> userOptional = userRepository.findById(id);

		if (!userOptional.isPresent())
			throw new UserNotFoundException("The requested user does not exist!");

		return userOptional.get().getPost();
	}

	// an end-point to return a particular user whose id will be passed as a path
	// variable or parameter
	@RequestMapping("/jpa/users/{id}")
	public Resource<User> oneUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);

		if (!user.isPresent())
			throw new UserNotFoundException("The requested user does not exist!"); // an exception to be thrown if the
																					// particular user id doesn't exist

		Resource<User> resource = new Resource<User>(user.get());
		ControllerLinkBuilder link = linkTo(methodOn(this.getClass()).listAllUsers());
		resource.add(link.withRel("list all users"));

		return resource;
	}

	// an end-point to add a new user
	@PostMapping("/jpa/users")
	public ResponseEntity<Object> addUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user); // saving a new user

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	// an end-point to delete a particular user whose id will be passed as a path
	// variable or parameter
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);

	}

}
