package com.training.userservice.controllers;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.userservice.dao.User;
import com.training.userservice.service.UserService;

@RestController
public class UserController {

	
	@Autowired
	UserService service;
	
	@GetMapping("/greet")
	public ResponseEntity<?> greet(@PathVariable String msg) {
		
		if(msg.equals("number")) {
			return new ResponseEntity<Integer>(123,HttpStatus.OK);
		}
		return new ResponseEntity<String>("Hello From UserService",HttpStatus.OK);
	}
	
	@GetMapping(value="/users")
	public ResponseEntity<List<User>> getUsers(){
		return new ResponseEntity<List<User>>(service.getUsers(), HttpStatus.OK);
	}
	
	@GetMapping("/user/{uid}")
	public ResponseEntity<User> getUserbyId(@PathVariable int uid) {	
		return new ResponseEntity<User>(service.getUserById(uid),HttpStatus.OK);
	}
	
	@GetMapping("/user")
	public ResponseEntity<User> getUserbyIdrequestparam(@RequestParam int uid) {
		return new ResponseEntity<User>(service.getUserById(uid),HttpStatus.OK);
	}
	
	@PostMapping("/save")
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		if(service.isUserPresent(user.getUserid())!=null) {
			return new ResponseEntity<User>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<User>(service.addUser(user),HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{userid}")
	public ResponseEntity<User> updateEntireUser(@PathVariable int userid,@RequestBody User user) {
		return new ResponseEntity<User>(service.updateUser(userid, user),HttpStatus.CREATED);
	}
	
	
	@PatchMapping("/updateuser/{userid}")
	public ResponseEntity<User> updateUser(@PathVariable int userid,@RequestBody User user) {
		return new ResponseEntity<User>(service.updatePatch(userid, user),HttpStatus.CREATED);
	}
	
	@PatchMapping("/updatefeild/{userId}")
	public ResponseEntity<User> updateUserFeild(@PathVariable Integer userId, @RequestBody Map<String, String> feildMap) {
		return new ResponseEntity<User>(service.updatePatchReflection(userId, feildMap),HttpStatus.CREATED);
	}
	
	
	@DeleteMapping(value = "/delete/{userid}")
	public ResponseEntity<String> deleteuser(@PathVariable int userid) {
		return new ResponseEntity<String>(service.deleteUser(userid),HttpStatus.ACCEPTED);
	}
}
