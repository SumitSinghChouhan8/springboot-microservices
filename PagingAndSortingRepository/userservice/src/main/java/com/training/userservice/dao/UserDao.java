package com.training.userservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserDao extends PagingAndSortingRepository<User, Integer>,CrudRepository<User, Integer>{

	public User findByusername(String username);
	
	public List<User> getByAddr(String addr);
	
	@Query(value =  "select * from user where email = :usermail",nativeQuery = true)
	public User getuseremail(String usermail);
	
	@Query(value = "select username from user where addr= :addr",nativeQuery = true)
	public List<String> getUsernamesByaddr(String addr);
	
	
	
}
