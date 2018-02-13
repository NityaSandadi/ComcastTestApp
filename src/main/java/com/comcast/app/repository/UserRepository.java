package com.comcast.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query("select u from User u where u.userName = ?1")
	User findByUserName(String userName);
	
	@Query("select u from User u where u.id = ?1")
	User findById(Integer id);
}
