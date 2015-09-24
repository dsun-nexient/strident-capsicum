package com.sunnyside.api.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.sunnyside.api.entity.Profile;
import com.sunnyside.api.jsonview.ProfileView;
import com.sunnyside.api.manager.ProfileManager;

@RestController
@CrossOrigin
@RequestMapping(value = "v1/profiles")
public class ProfileController {
	
	@Autowired
	private ProfileManager profileManager;
	
	@RequestMapping(method = RequestMethod.POST)
	public Profile create(@RequestBody final Profile profile) {
		return profileManager.updateOrDelete(profile);
	}
	
	@JsonView(ProfileView.ProfileFriends.class)
	@RequestMapping(value = "/{profileId}", method = RequestMethod.GET)
	public Profile read(@PathVariable final Integer profileId) {
		return profileManager.read(profileId);
	}
	
	@JsonView(ProfileView.ProfileFriends.class)
	@RequestMapping(method = RequestMethod.GET)
	public Collection<Profile> list() {
		return profileManager.list();
	}
	
	@RequestMapping(value = "/{profileId}", method = RequestMethod.PUT)
	public Profile update(@RequestBody final Profile profile, @PathVariable final Integer profileId) {
		return profileManager.updateOrDelete(profile);
	}
	
	@RequestMapping(value = "/{profileId}", method = RequestMethod.DELETE)
	public void delete(@PathVariable final Integer profileId) {
		profileManager.delete(profileId);
	}
	
}
