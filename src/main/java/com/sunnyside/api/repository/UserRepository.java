package com.sunnyside.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sunnyside.api.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
