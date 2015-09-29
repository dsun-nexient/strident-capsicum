package com.sunnyside.api.controller;

import java.util.Collection;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.sunnyside.api.controller.exception.ResourceNotFoundException;
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
		Mockito.when(userManager.createOrUpdate(Mockito.<User>any())).thenReturn(user);
		Mockito.when(userManager.read(Mockito.anyInt())).thenReturn(user);
		Mockito.when(userManager.list()).thenReturn(users);
	}

	// Test method identifier convention
	// methodName_Given{Type}_PerformLogic_Returns{Type}
	// if no {Type} omit
	// if does not return anything omit Returns

	@Test
	public void create_GivenUser_Persist_ReturnsUser() {
		Assert.assertEquals("UserController.create() did not return the same User as the arg", user,
				userController.create(user));
		Mockito.verify(userManager, Mockito.times(1)).createOrUpdate(user);
	}

	@Test
	public void read_GivenInteger_Find_ReturnsUser() throws ResourceNotFoundException {
		Assert.assertEquals("UserController.read() did not return valid User", user,
				userController.read(Mockito.anyInt()));
		Mockito.verify(userManager, Mockito.times(1)).read(Mockito.anyInt());

	}

	@Test(expected = ResourceNotFoundException.class)
	public void read_GivenInteger_ThrowException() throws ResourceNotFoundException {
		Mockito.when(userManager.read(Mockito.anyInt())).thenReturn(null);
		userController.read(1);
		Mockito.verify(userManager, Mockito.times(1)).read(1);
	}

	@Test
	public void list_listAll_ReturnsCollectionUser() {
		Assertions.assertThat(userController.list()).isEqualTo(users);
		Mockito.verify(userManager, Mockito.times(1)).list();
	}
	
	@Test
	public void update_GivenUserAndInteger_CheckIfUserExistsAndUpdateUser_ReturnsUser() throws ResourceNotFoundException {
		Assertions.assertThat(userController.update(user, 1)).isEqualTo(user);
	}

}
