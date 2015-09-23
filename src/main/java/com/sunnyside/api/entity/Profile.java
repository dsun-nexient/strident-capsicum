package com.sunnyside.api.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.sunnyside.api.dto.ProfileDTO;
import com.sunnyside.api.jsonview.ProfileView;

@Entity
@Table(name = "profile")
public class Profile extends BaseEntity{
	
	@JsonView(ProfileView.ProfileFriends.class)
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@JsonView(ProfileView.ProfileFriends.class)
	@Column(name = "created")
	private Timestamp created;
	
	@JsonView(ProfileView.ProfileFriends.class)
	@Column(name = "first_name")
	private String firstName;
	
	@JsonView(ProfileView.ProfileFriends.class)
	@Column(name = "last_name")
	private String lastName;
	
	@JsonView(ProfileView.ProfileFriends.class)
	@Column(name = "date_of_birth")
	@Type(type = "date")
	private Date dateOfBirth;
	
	@JsonIgnore
	@OneToMany(mappedBy = "profile", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Collection<Blog> blogs; 
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable
	(name = "friends", 
	joinColumns = {@JoinColumn(name = "profile_id")},
	inverseJoinColumns = @JoinColumn(name = "friend_profile_id"))
	private Collection<Profile> friendsReference;
	
	//Transient DTO Collection only used for serialization
	@Transient
	@JsonView(ProfileView.ProfileFriends.class)
	private Collection<ProfileDTO> friends;
	
	/*
	 * Getters and setters are used by:
	 * -Jackson for serialization and deserialization
	 * -Hibernate for data persistence and retreival
	 */
	
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
	public Collection<Blog> getBlogs() {
		return blogs;
	}
	public void setBlogs(Collection<Blog> blogs) {
		this.blogs = blogs;
	}
	public Collection<Profile> getFriendsReference() {
		return friendsReference;
	}
	public void setFriendsReference(Collection<Profile> friends) {
		this.friendsReference = friends;
	}
	public Collection<ProfileDTO> getFriends() {
		friends = new ArrayList<>();
		ProfileDTO profileDTO;
		for (Profile friend : friendsReference) {
			profileDTO = new ProfileDTO()
					.setUser(friend.getUser())
					.setFirstName(friend.getFirstName())
					.setLastName(friend.getLastName())
					.setDateOfBirth(friend.getDateOfBirth())
					.setCreated(friend.getCreated());
			friends.add(profileDTO);
		}
		return friends;
	}
	public void setFriends(Collection<ProfileDTO> friendsDTO) {
		this.friends = friendsDTO;
	}
}
