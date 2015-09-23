package com.sunnyside.api.manager;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunnyside.api.entity.Blog;
import com.sunnyside.api.entity.Profile;
import com.sunnyside.api.repository.BlogRepository;
import com.sunnyside.api.repository.ProfileRepository;

@Service
public class ProfileManager {
	
	@Autowired
	private ProfileRepository profileRepository;
	
	@Autowired
	private BlogRepository blogRepository;

	public Profile updateOrDelete(final Profile profile) {
		return profileRepository.save(profile);
	}
	
	public Profile read(final Integer profileId) {
		return profileRepository.findOne(profileId);
	}
	
	public Collection<Blog> listBlogs(final Integer profileId) {
		return blogRepository.findByProfileId(profileId);
	}
	
	public Blog readBlog(final Integer profileId, final Integer profileBlogId) {
		return blogRepository.findByProfileIdAndProfileBlogId(profileId, profileBlogId);
	}
	
	public Collection<Profile> list() {
		return profileRepository.findAll();
	}
	
	public void delete(final Integer profileId) {
		profileRepository.delete(profileId);
	}
}
