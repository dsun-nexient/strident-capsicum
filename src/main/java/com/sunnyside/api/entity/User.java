package com.sunnyside.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sunnyside.api.enumerated.Role;

@Entity
@Table(name = "user")
public class User extends BaseEntity{
	
	@JsonIgnore
	@Enumerated(EnumType.STRING)
	@Column(name = "role")
	private Role role;
	@Column(name = "username")
	private String userame;
	@JsonIgnore
	@Column(name = "password")
	private String password;
	@JsonIgnore
	@Column(name = "is_active")
	private Boolean isActive;
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getUserame() {
		return userame;
	}
	public void setUserame(String userame) {
		this.userame = userame;
	}
	public String getPassword() {
		return password;
	}
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
