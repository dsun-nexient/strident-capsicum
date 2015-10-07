package com.sunnyside.api.controller;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.sunnyside.api.controller.exception.PageNotFoundException;
import com.sunnyside.api.controller.exception.ResourceNotFoundException;
import com.sunnyside.api.entity.User;
import com.sunnyside.api.manager.UserManager;

public class UserControllerTest {

	private static final int VALID_USER_ID = 0;
	private static final int VALID_PAGE_NUMBER = 1;
	private static final int INVALID_USER_ID = 1;
	private static final int PAGE_NUMBER_LESS_THAN_ONE = 0;

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
		Mockito.when(userManager.createOrUpdate(Mockito.any(User.class))).thenReturn(user);
		Mockito.when(userManager.read(VALID_USER_ID)).thenReturn(user);
		Mockito.when(userManager.list()).thenReturn(users);
		Mockito.when(userManager.list(VALID_PAGE_NUMBER)).thenReturn(users);
	}


	
	
	@Test
	public void testCreateShouldReturnUserGivenValidUser() {
		Assert.assertEquals(user, userController.create(user));
		Mockito.verify(userManager, Mockito.times(1)).createOrUpdate(user);
	}
	
	
	
	
	@Test(expected = ResourceNotFoundException.class)
	public void testReadShouldThrowResourceNotFoundExceptionGivenInvalidUserId() throws ResourceNotFoundException {
		userController.read(INVALID_USER_ID);
	}

	@Test
	public void testReadShouldReturnUserGivenValidUserId() throws ResourceNotFoundException {
		Assert.assertEquals(user, userController.read(VALID_USER_ID));
	}

	
	
	
	@Test
	public void testListShouldReturnCollectionOfUsersIfPageNumberIsNull() throws PageNotFoundException {
		Assert.assertEquals(users, userController.list(null));
		Mockito.verify(userManager, Mockito.times(1)).list();
	}

	@Test(expected = PageNotFoundException.class)
	public void testListShouldThrowPageNotFoundExceptionIfPageNumberLessThanOne() throws PageNotFoundException {
		userController.list(PAGE_NUMBER_LESS_THAN_ONE);
	}
	
	@Test
	public void testListShouldReturnCollectionOfUsersGivenValidPageNumber() throws PageNotFoundException {
		Assert.assertEquals(users, userController.list(VALID_PAGE_NUMBER));
		Mockito.verify(userManager, Mockito.times(1)).list(VALID_PAGE_NUMBER);
		Mockito.verify(userManager, Mockito.never()).list();
	}
	
	
	
	
	@Test(expected = ResourceNotFoundException.class)
	public void testUpdateShouldThrowResourceNotFoundExceptionGivenInvalidUserId() throws ResourceNotFoundException {
		userController.update(user, INVALID_USER_ID);
	}
	
	@Test
	public void testUpdateShouldInvokeUserSetId() throws ResourceNotFoundException {
		userController.update(user, VALID_USER_ID);
		Mockito.verify(user, Mockito.times(1)).setId(VALID_USER_ID);
	}
	
	@Test
	public void testUpdateShouldReturnUserGivenValidUserId() throws ResourceNotFoundException {
		Assert.assertEquals(user, userController.update(user, VALID_USER_ID));
		Mockito.verify(userManager, Mockito.times(1)).createOrUpdate(user);
	}
	
	
	
	
	@Test
	public void testDeleteShouldInvokeUserManagerDeleteById() {
		userController.delete(VALID_USER_ID);
		Mockito.verify(userManager, Mockito.times(1)).deleteById(VALID_USER_ID);
	}
}
