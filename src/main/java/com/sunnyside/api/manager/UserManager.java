package com.sunnyside.api.manager;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sunnyside.api.entity.User;
import com.sunnyside.api.repository.UserRepository;

@Service
public class UserManager {
		
	@Autowired
	private Md5PasswordEncoder encoder;
	@Autowired
	@Qualifier("salt")
	private String salt;
	@Autowired
	private UserRepository userRepository;
	
	private final int PAGE_SIZE = 3;
	
	public User read(final Integer userId) {
		return userRepository.findOne(userId);
	}
	
	public Collection<User> list() {
		return userRepository.findAll();
	}
	
	public Collection<User> list(Integer pageNumber) {
		PageRequest pageRequest = new PageRequest(pageNumber - 1, PAGE_SIZE, new Sort("username"));
		Page<User> userPage = userRepository.findAll(pageRequest);
		return userPage.getContent();
	}
	
	public User createOrUpdate(final User user) {
		user.setPassword(encoder.encodePassword(user.getPassword(), salt));
		return userRepository.save(user);
	}
	
	public void deleteById(final Integer userId) {
		userRepository.delete(userId);
	}
	
	public void deleteByUser(final User user) {
		userRepository.delete(user);
	}
}
