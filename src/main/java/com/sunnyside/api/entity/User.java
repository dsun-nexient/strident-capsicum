package com.sunnyside.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.sunnyside.api.enumerated.Role;
import com.sunnyside.api.jsonview.ProfileView;

@Entity
@Table(name = "user")
public class User extends BaseEntity{
	
	@JsonView(ProfileView.ProfileFriends.class)
	@Enumerated(EnumType.STRING)
	@Column(name = "role")
	private Role role;
	
	@JsonView(ProfileView.ProfileFriends.class)
	@Column(name = "username")
	private String username;
	@JsonIgnore
	@Column(name = "password")
	private String password;
	
	@JsonView(ProfileView.ProfileFriends.class)
	@Column(name = "is_active")
	private Boolean isActive;
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
}
