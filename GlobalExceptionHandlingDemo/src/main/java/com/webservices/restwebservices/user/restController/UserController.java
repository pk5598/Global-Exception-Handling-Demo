package com.webservices.restwebservices.user.restController;

import java.net.URI;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

import javax.validation.Valid;


import org.hibernate.EntityMode;
import org.hibernate.boot.jaxb.hbm.internal.EntityModeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.webservices.restwebservices.exception.UserNotFoundException;
import com.webservices.restwebservices.user.User;
import com.webservices.restwebservices.user.dao.UserDaoService;

@RestController
public class UserController {
	
	@Autowired
	UserDaoService userDaoService;
	
	@GetMapping("users")
	public List<User> retrieveAllUser() {
		return userDaoService.findAll();
	}
	
	@GetMapping("/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id) {
		User user= userDaoService.findOne(id);
		if (user==null) {
			throw new UserNotFoundException("id-"+id);	
		}
		//"all-users", SERVER_PATH + "/users"
				//retrieveAllUsers
		EntityModel<User> model=EntityModel.of(user);
		WebMvcLinkBuilder linkToUser=
		WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUser());
		
		model.add(linkToUser.withRel("all-users"));
		
		return model;
	}
	

	//
	// input - details of user
	// output - CREATED & Return the created URI
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
		User savedUser = userDaoService.save(user);
		
		
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId()).toUri();
			
			return ResponseEntity.created(location).build();

	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user= userDaoService.deleteById(id);
		if (user==null) {
			throw new UserNotFoundException("id-"+id);
			
		}
		
		
	}
	
	
	

}
