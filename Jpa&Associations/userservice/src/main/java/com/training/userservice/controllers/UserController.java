package com.training.userservice.controllers;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
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
	
	@GetMapping(value = "/greet")
	public ResponseEntity<?> greet() {
		return new ResponseEntity<String>("<h1> Hello From UserService </h1>",HttpStatus.OK);
	}
	
	@GetMapping(value="/users")
	public ResponseEntity<List<User>> getUsers(){
		return new ResponseEntity<List<User>>(service.getUsers(),HttpStatus.OK);
	}
	
	@GetMapping("/userbysort/{feild}/{sorttype}")
	public ResponseEntity<List<User>> getUserBySort(@PathVariable String feild, @PathVariable String sorttype){
		return new ResponseEntity<>(service.getUserBySort(feild,sorttype),HttpStatus.OK);
	}
	
	@GetMapping("/userbypage/{pageSize}/{pageNumber}")
	public ResponseEntity<List<User>> getUserPage(@PathVariable int pageNumber,@PathVariable int pageSize){
		return new ResponseEntity<List<User>>(service.getUsersByPage(pageNumber,pageSize),HttpStatus.OK);
	}
	
	
	@GetMapping("/user/{uid}")
	public ResponseEntity<User> getUserbyId(@PathVariable int uid) {	
		return new ResponseEntity<User>(service.getUserById(uid),HttpStatus.OK);
	}
	
	@GetMapping("/userbyname/{username}")
	public ResponseEntity<User> getUserByName(@PathVariable String username){
		return new ResponseEntity<User>(service.getUserByname(username),HttpStatus.OK);
	}
	
	
	
	@GetMapping("/userbyemail/{email}")
	public ResponseEntity<User> getuseremail(@PathVariable String email){
		return new ResponseEntity<User>(service.getuseremail(email),HttpStatus.OK);
	}
	
	
	@PostMapping(value = "/save")
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		return new ResponseEntity<User>(service.addUser(user),HttpStatus.CREATED);
	}
	
	
	
	
	@PutMapping("/update/{userid}")
	public ResponseEntity<User> updateEntireUser(@PathVariable int userid,@RequestBody User user) {
		return new ResponseEntity<User>(service.updateuser(userid, user),HttpStatus.CREATED);
	}
	
	
	@DeleteMapping(value = "/delete/{userid}")
	public ResponseEntity<String> deleteuser(@PathVariable int userid) {
		return new ResponseEntity<String>(service.deleteUser(userid),HttpStatus.ACCEPTED);
	}
}
