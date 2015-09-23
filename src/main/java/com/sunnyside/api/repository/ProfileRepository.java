package com.sunnyside.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sunnyside.api.entity.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer>{

}
