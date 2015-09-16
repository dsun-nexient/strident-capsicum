package com.sunnyside.api.entity;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "profile")
public class Profile extends BaseEntity{
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	@Column(name = "created")
	private Timestamp created;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "date_of_birth")
	@Type(type = "date")
	private Date dateOfBirth;
	@OneToMany(mappedBy = "profile")
	private Collection<Blog> blogs; 
	@ManyToMany
	@JoinTable
	(name = "friends", 
	joinColumns = {@JoinColumn(name = "profile_id")},
	inverseJoinColumns = {@JoinColumn(name = "friend_profile_id")})
	private Collection<Profile> friends;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Timestamp getCreated() {
		return created;
	}
	public void setCreated(Timestamp created) {
		this.created = created;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
}
