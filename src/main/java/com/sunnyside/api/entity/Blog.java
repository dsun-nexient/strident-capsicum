package com.sunnyside.api.entity;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.sunnyside.api.jsonview.ProfileView.ProfileBlog;

@Entity
@Table(name = "blog")
public class Blog {

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "profile_id")
	private Profile profile;
	
	@JsonView(ProfileBlog.class)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "profile_blog_id")
	private Integer profileBlogId;
	
	@JsonView(ProfileBlog.class)
	@Column(name = "created")
	private Timestamp created;
	
	@JsonView(ProfileBlog.class)
	@Column(name = "content")
	private String content;
	
	
	public Profile getProfile() {
		return profile;
	}
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	public Integer getProfileBlogId() {
		return profileBlogId;
	}
	public void setProfileBlogId(Integer profileBlogId) {
		this.profileBlogId = profileBlogId;
	}
	public Timestamp getCreated() {
		return created;
	}
	public void setCreated(Timestamp created) {
		this.created = created;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
