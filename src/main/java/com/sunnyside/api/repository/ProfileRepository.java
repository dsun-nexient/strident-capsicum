package com.sunnyside.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sunnyside.api.entity.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer>{
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM friends WHERE profile_id = :profileId OR friend_profile_id = :profileId", nativeQuery = true)
	public void deleteFriendsByProfileId(@Param(value = "profileId") Integer profileId);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM profile WHERE id = :profileId", nativeQuery = true)
	public void deleteByProfileId(@Param(value = "profileId") Integer profileId);
}
