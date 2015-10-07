package com.sunnyside.api.controller;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sunnyside.api.controller.exception.PageNotFoundException;
import com.sunnyside.api.controller.exception.ResourceNotFoundException;
import com.sunnyside.api.entity.User;
import com.sunnyside.api.manager.UserManager;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
@RequestMapping(value = "v1/users")
public class UserController {

	@Autowired
	private UserManager userManager;

	
	
	@RequestMapping(
			method = RequestMethod.POST, 
			consumes = "application/json", 
			produces = "application/json"
			)
	@ApiOperation(
			value = "Adds new User entity into the persistence context.",
			code = 200
			)
	public User create(@RequestBody final User user) {
		//Ideally A validator would be nice here
		return userManager.createOrUpdate(user);
	}

	
	
	@RequestMapping(
			value = "/{userId}",
			method = RequestMethod.GET,
			produces = "application/json"
			)
	@ApiOperation(
			value = "Returns User entity with the given userId.",
			code = 200
			)
	public User read(@PathVariable final Integer userId) throws ResourceNotFoundException {
		Optional<User> optional = Optional.ofNullable(userManager.read(userId));
		return optional.orElseThrow(() -> new ResourceNotFoundException(601, "No resource found with the given id: " + userId));
	}

	
	
	@RequestMapping(
			method = RequestMethod.GET,
			produces = "application/json"
			)
	@ApiOperation(
			value = "Returns a list of all User entities.",
			code = 200
			)
	public Collection<User> list(@RequestParam(value = "page", required = false) Integer pageNumber) throws PageNotFoundException {
		if (pageNumber == null) {
			return userManager.list();
		} else if (pageNumber < 1) {
			throw new PageNotFoundException(700, "Page index can not be less than one");
		}
		
		return userManager.list(pageNumber);
	}
	
	
	

	
	
	@RequestMapping(
			value = "/{userId}",
			method = RequestMethod.PUT,
			consumes = "application/json",
			produces = "application/json"
			)
	@ApiOperation(
			value = "Updates an existing User entity with the given userId.",
			code = 200
			)
	public User update(@RequestBody final User user, @PathVariable final Integer userId) throws ResourceNotFoundException {
		Optional<User> optional = Optional.ofNullable(userManager.read(userId));
		User existingUser = optional.orElseThrow(() -> new ResourceNotFoundException(602, "No existing resource to update with the given id: " + userId));
		user.setId(existingUser.getId());
		return userManager.createOrUpdate(user);
	}

	
	
	@RequestMapping(
			value = "/{userId}",
			method = RequestMethod.DELETE
			)
	@ApiOperation(
			value = "Detaches an User entity with the given userId from the persistence context.",
			code = 200
			)
	public void delete(@PathVariable final Integer userId) {
		userManager.deleteById(userId);
	}

	
	
}
