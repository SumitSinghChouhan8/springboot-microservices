package com.training.userservice.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.training.userservice.dao.User;
import com.training.userservice.dao.UserDao;
import com.training.userservice.exceptions.UserNotFoundException;

@Service
public class UserService {
	
	@Autowired
	UserDao dao;
	
	public List<User> getUsers(){
		return (List<User>) dao.findAll();
	}
	
	
	public List<User> getUsersByPage(int pagenumber,int pageSize){
		Pageable pagable = PageRequest.of(pagenumber, pageSize);
		
		//we can add sorting at page level also
		//Pageable pagable = PageRequest.of(pagenumber, pageSize,Sort.by("username"));
		
		Page<User> page= dao.findAll(pagable);
		if(page.isEmpty()) {
			throw new RuntimeException("No Records in this Page");
		}
		return page.toList();
	}
	
	public List<User> getUserBySort(String feild,String sorttype){
//		if(sorttype.equals("dsc")) {
//			return (List<User>) dao.findAll(Sort.by(feild).descending());
//		}
//		return (List<User>) dao.findAll(Sort.by(feild));
		
		return (sorttype.equals("dsc")) ? 
				(List<User>) dao.findAll(Sort.by(feild).descending()):
					(List<User>) dao.findAll(Sort.by(feild));
	}
	
	
	
	public User getUserById(int userid) {
		 return dao.findById(userid).orElseThrow(()->new UserNotFoundException("User Not Found"));
	}
	
	public User getUserByname(String username) {
		return dao.findByusername(username);
	}
	
	public User getuseremail(String email) {
		return dao.getuseremail(email);
	}
	
	
	
	public boolean isUserPresent(int userId) {
			return  dao.existsById(userId);
	}
	
	
	public User addUser(User user) {
		return dao.save(user);
	}
	
	
	public User updateuser(int usertId,User user) {
		User existingUser= getUserById(usertId);
		if(existingUser!=null) {
			existingUser.setUsername(user.getUsername()!=null?user.getUsername():existingUser.getUsername());
			existingUser.setAddr(user.getAddr()!=null?user.getAddr():existingUser.getAddr());
			existingUser.setEmail(user.getEmail()!=null?user.getEmail():existingUser.getEmail());
		}
		
		return dao.save(existingUser);
	}
	
	
	public String deleteUser(int userid) {
		dao.deleteById(userid); 
		return "User Deleted with UserID :: "+userid;
	}
	
	
	
}
