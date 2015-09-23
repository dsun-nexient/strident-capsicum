package com.sunnyside.api.dto;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.sunnyside.api.entity.Blog;
import com.sunnyside.api.entity.User;
import com.sunnyside.api.jsonview.ProfileView;

@SuppressWarnings("unused")
public class ProfileDTO {

	@JsonView(ProfileView.ProfileFriends.class)
	private User user;
	@JsonView(ProfileView.ProfileFriends.class)
	private Timestamp created;
	@JsonView(ProfileView.ProfileFriends.class)
	private String firstName;
	@JsonView(ProfileView.ProfileFriends.class)
	private String lastName;
	@JsonView(ProfileView.ProfileFriends.class)
	private Date dateOfBirth;
	
	public User getUser() {
		return user;
	}
	public ProfileDTO setUser(User user) {
		this.user = user;
		return this;
	}
	public Timestamp getCreated() {
		return created;
	}
	public ProfileDTO setCreated(Timestamp created) {
		this.created = created;
		return this;
	}
	public String getFirstName() {
		return firstName;
	}
	public ProfileDTO setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}
	public String getLastName() {
		return lastName;
	}
	public ProfileDTO setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public ProfileDTO setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;		
		return this;
	}
	
}
