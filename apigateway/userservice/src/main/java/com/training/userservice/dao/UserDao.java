package com.training.userservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserDao extends JpaRepository<User, Integer>{

	public User findByusername(String username);
	
	@Query(value =  "select * from user where email = :usermail",nativeQuery = true)
	public User getuseremail(String usermail);
	
	
	
	
}
