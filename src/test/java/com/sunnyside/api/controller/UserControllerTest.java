package com.sunnyside.api.controller;

import java.util.Collection;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.sunnyside.api.entity.User;
import com.sunnyside.api.manager.UserManager;

public class UserControllerTest {
	@Mock
	private User user;
	@Mock
	private Collection<User> users;
	@Mock
	private UserManager userManager;
	@InjectMocks
	private UserController userController;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);	
		Mockito.when(userManager.createOrUpdate(Matchers.any(User.class))).thenReturn(user);
		Mockito.when(userManager.read(Matchers.anyInt())).thenReturn(user);
		Mockito.when(userManager.list()).thenReturn(users);
	}
	
	/*
	 * Convention for naming tests:
	 * [the name of the tested method]_[expected input / tested state]_[expected behavior]
	 */
	
	
	/**
	 * This test is pointless... As it is testing something that will all ways be true base on method signature
	 */
	@Test
	public void create_GivenUser_ShouldReturnUser() {
		Assert.assertThat(userController.create(user), CoreMatchers.instanceOf(User.class));
		Mockito.verify(userManager, Mockito.times(1)).createOrUpdate(user);
	}
	
	/**
	 * This test asserts that the returned user is indeed the same user instance
	 */
	@Test
	public void create_GivenUser_ShouldReturnSameUser() {
		Assert.assertEquals(userController.create(user), user);
		Mockito.verify(userManager, Mockito.times(1)).createOrUpdate(user);
	}
	
	
	@Test
	public void list_NoParam_ShouldReturnUserList() {
		Assert.assertEquals(userController.list(), users);
	}
}
