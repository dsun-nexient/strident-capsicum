package com.sunnyside.api.controller;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.sunnyside.api.controller.exception.ResourceNotFoundException;
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
		return profileManager.updateOrSave(profile);
	}

	@JsonView(ProfileView.ProfileFriends.class)
	@RequestMapping(value = "/{profileId}", method = RequestMethod.GET)
	public Profile read(@PathVariable final Integer profileId) throws ResourceNotFoundException {
		Optional<Profile> optionalProfile = Optional.ofNullable(profileManager.read(profileId));
		return optionalProfile.orElseThrow(new Supplier<ResourceNotFoundException>() {
			@Override
			public ResourceNotFoundException get() {
				return new ResourceNotFoundException(600, "No entity to be deleted with the given id: " + profileId);
			}
		});
	}

	@JsonView(ProfileView.ProfileFriends.class)
	@RequestMapping(method = RequestMethod.GET)
	public Collection<Profile> list() {
		return profileManager.list();
	}

	@RequestMapping(value = "/{profileId}", method = RequestMethod.PUT)
	public Profile update(@RequestBody final Profile profile, @PathVariable final Integer profileId) {
		return profileManager.updateOrSave(profile);
	}

	@RequestMapping(value = "/{profileId}", method = RequestMethod.DELETE)
	public void delete(@PathVariable final Integer profileId) throws ResourceNotFoundException {
		Optional.ofNullable(profileManager.read(profileId)).orElseThrow(
				() -> new ResourceNotFoundException(601, "No entity to be deleted with the given id: " + profileId));
		profileManager.delete(profileId);
	}

}
