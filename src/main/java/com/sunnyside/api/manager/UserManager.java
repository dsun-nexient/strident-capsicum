package com.sunnyside.api.manager;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunnyside.api.entity.User;
import com.sunnyside.api.repository.UserRepository;

@Service
public class UserManager {
	
	@Autowired
	private UserRepository userRepository;
	
	public User read(final Integer userId) {
		return userRepository.findOne(userId);
	}
	
	public Collection<User> list() {
		return userRepository.findAll();
	}
	
	public User createOrUpdate(final User user) {
		return userRepository.save(user);
	}
	
	public void deleteById(final Integer userId) {
		userRepository.delete(userId);
	}
	
	public void deleteByUser(final User user) {
		userRepository.delete(user);
	}
}
