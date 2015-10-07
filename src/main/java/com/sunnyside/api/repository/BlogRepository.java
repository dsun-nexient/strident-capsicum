package com.sunnyside.api.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.sunnyside.api.entity.Blog;

public interface BlogRepository extends JpaRepository<Blog, Integer> {
	
	
	public Collection<Blog> findByProfileId(Integer profileId);
	
	public Blog findByProfileIdAndProfileBlogId(Integer profileId, Integer profileBlogId);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM blog WHERE profile_id = :profileId AND profile_blog_id = :profileBlogId", nativeQuery = true)
	public void deleteByProfileIdAndProfileBlogId(@Param("profileId") Integer profileId, @Param("profileBlogId") Integer profileBlogId);
}
