package com.sunnyside.api.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunnyside.api.entity.Blog;

public interface BlogRepository extends JpaRepository<Blog, Integer> {
	
	
	public Collection<Blog> findByProfileId(Integer profileId);
	
	public Blog findByProfileIdAndProfileBlogId(Integer profileId, Integer profileBlogId);
}
