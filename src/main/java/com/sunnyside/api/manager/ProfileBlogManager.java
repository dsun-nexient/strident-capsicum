package com.sunnyside.api.manager;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunnyside.api.entity.Blog;
import com.sunnyside.api.repository.BlogRepository;

@Service
public class ProfileBlogManager {
	
	@Autowired
	private BlogRepository blogRepository;
	
	
	public Collection<Blog> listBlogs(final Integer profileId) {
		return blogRepository.findByProfileId(profileId);
	}
	
	public Blog readBlog(final Integer profileId, final Integer profileBlogId) {
		return blogRepository.findByProfileIdAndProfileBlogId(profileId, profileBlogId);
	}
	
}
