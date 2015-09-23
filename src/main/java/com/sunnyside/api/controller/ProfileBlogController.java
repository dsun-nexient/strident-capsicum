package com.sunnyside.api.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.sunnyside.api.entity.Blog;
import com.sunnyside.api.jsonview.ProfileView.ProfileBlog;
import com.sunnyside.api.manager.ProfileManager;

@RestController
@CrossOrigin
@RequestMapping(value = "v1/profiles")
public class ProfileBlogController {
	
	@Autowired
	ProfileManager profileManager;
	
	@JsonView(ProfileBlog.class)
	@RequestMapping(value = "/{profileId}/blogs", method = RequestMethod.GET)
	public Collection<Blog> listProfileBlogs(@PathVariable final Integer profileId) {
		return profileManager.listBlogs(profileId);
	}
	
	@JsonView(ProfileBlog.class)
	@RequestMapping(value = "/{profileId}/blogs/{profileBlogId}", method = RequestMethod.GET)
	public Blog readProfileBlog(@PathVariable final Integer profileId, @PathVariable final Integer profileBlogId) {
		return profileManager.readBlog(profileId, profileBlogId);
	}
	
}
