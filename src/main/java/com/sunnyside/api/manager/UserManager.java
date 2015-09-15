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
	
	public User read(Integer userId) {
		return userRepository.findOne(userId);
	}
	
	public Collection<User> list() {
		return userRepository.findAll();
	}
}
