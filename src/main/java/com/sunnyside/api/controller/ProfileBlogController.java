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

import com.fasterxml.jackson.annotation.JsonView;
import com.sunnyside.api.controller.exception.ResourceNotFoundException;
import com.sunnyside.api.entity.Blog;
import com.sunnyside.api.entity.Profile;
import com.sunnyside.api.jsonview.ProfileView.ProfileBlog;
import com.sunnyside.api.manager.ProfileBlogManager;
import com.sunnyside.api.manager.ProfileManager;

@RestController
@CrossOrigin
@RequestMapping(value = "v1/profiles")
public class ProfileBlogController {
	
	@Autowired
	private ProfileManager profileManager;
	
	@Autowired
	private ProfileBlogManager profileBlogManager;
	
	
	
	@JsonView(ProfileBlog.class)
	@RequestMapping(value = "/{profileId}/blogs", method = RequestMethod.GET)
	public Collection<Blog> listProfileBlogs(@PathVariable final Integer profileId) {
		
		Collection<Blog> blogs = profileBlogManager.listBlogs(profileId);
		
		return blogs;
	}
	
	
	
	@JsonView(ProfileBlog.class)
	@RequestMapping(value = "/{profileId}/blogs/{profileBlogId}", method = RequestMethod.GET)
	public Blog readProfileBlog(@PathVariable final Integer profileId, @PathVariable final Integer profileBlogId) throws ResourceNotFoundException {
		
		Optional<Blog> optionalBlog = Optional.ofNullable(profileBlogManager.readBlog(profileId, profileBlogId));
		
		return optionalBlog.orElseThrow(() -> new ResourceNotFoundException(600, " No existing resource to read with the given profilelBlogId: " + profileBlogId));
	}
	
	
	
	@RequestMapping(value = "/{profileId}/blogs", method = RequestMethod.POST)
	public Blog createProfileBlog(@PathVariable final Integer profileId, @RequestBody final Blog blog) throws ResourceNotFoundException {
		
		Optional<Profile> optional = Optional.ofNullable(profileManager.read(profileId));
		
		Profile managedProfile = optional.orElseThrow(() -> new ResourceNotFoundException(603, " No existing resource to read with the given profileId: " + profileId));
		
		blog.setProfile(managedProfile);
		
		managedProfile.getBlogs().add(blog);
				
		profileManager.updateOrSave(managedProfile);
		
		return blog;
	}
	
	
	
	@RequestMapping(value = "/{profileId}/blogs/{profileBlogId}", method = RequestMethod.DELETE)
	public void deleteProfileBlog(@PathVariable final Integer profileId, @PathVariable final Integer profileBlogId) throws ResourceNotFoundException {
				
		Optional.ofNullable(profileBlogManager.readBlog(profileId, profileBlogId)).orElseThrow(() -> new ResourceNotFoundException(603, " No existing resource to read with the given id: " + profileId));
		
		profileBlogManager.deleteBlog(profileId, profileBlogId);
	}
	
}
