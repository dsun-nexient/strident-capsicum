package com.sunnyside.api.controller;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sunnyside.api.controller.exception.ResourceNotFoundException;
import com.sunnyside.api.entity.User;
import com.sunnyside.api.manager.UserManager;

@RestController
@CrossOrigin
@RequestMapping(value = "v1/users")
public class UserController {

	@Autowired
	private UserManager userManager;

	@RequestMapping(method = RequestMethod.POST)
	public User create(@RequestBody final User user) {
		return userManager.createOrUpdate(user);
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public User read(@PathVariable final Integer userId) throws ResourceNotFoundException {
		Optional<User> optional = Optional.ofNullable(userManager.read(userId));
		return optional.orElseThrow(() -> new ResourceNotFoundException(601, "No resource found with the given id: " + userId));
	}

	@RequestMapping(method = RequestMethod.GET)
	public Collection<User> list() {
		return userManager.list();
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
	public User update(@RequestBody final User user, @PathVariable final Integer userId) {
		return userManager.createOrUpdate(user);
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
	public void delete(@PathVariable final Integer userId) {
		userManager.deleteById(userId);
	}

}
