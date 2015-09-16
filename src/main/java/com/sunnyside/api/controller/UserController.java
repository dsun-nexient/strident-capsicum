package com.sunnyside.api.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sunnyside.api.entity.User;
import com.sunnyside.api.manager.UserManager;

@RestController
@RequestMapping(value = "v1/user")
public class UserController {
	
	@Autowired
	private UserManager userManager;
	
	@RequestMapping(method = RequestMethod.POST)
	public User create(@RequestBody final User user) {
		return userManager.createOrUpdate(user);
	}
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public User read(@PathVariable final Integer userId) {
		return userManager.read(userId);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public Collection<User> list() {
		return userManager.list();
	}
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
	public User update(@RequestBody final User user, @PathVariable final Integer userId) {
		final User readUser = userManager.read(userId);
		Assert.notNull(readUser, "userManager.read() in UserController.update() came back as null");
		return userManager.createOrUpdate(user);
	}
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
	public void delete(@PathVariable final Integer userId) {
		userManager.deleteById(userId);
	}
	
}
