package com.training.userservice.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.web.client.RestTemplate;

import com.training.userservice.dao.User;
import com.training.userservice.dao.UserDao;
import com.training.userservice.dto.Orders;
import com.training.userservice.dto.UserDto;
import com.training.userservice.service.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class UserController {

	
	@Autowired
	UserService service;
	
	@Autowired
	RestTemplate resttemplate;
	
	@Value("${orderservice.url}")
	String orderserviceurl;
	
	@Retry(name = "ordergreetfallback",fallbackMethod = "fallbackforordergreet")
	//@CircuitBreaker(name = "ordergreetfallback",fallbackMethod = "fallbackforordergreet")
	@GetMapping("/ordergreet")
	public String grertFromOrderService() {
		System.out.println("======= OrderGreet Called ==============");
		return resttemplate.getForObject(orderserviceurl+"/greet", String.class);
	}
	
	public String fallbackforordergreet(Throwable t) {
		return "<h1>Currently order service is not working ..... come after decade because our Dev are sleeping !!!</h1>";
	}
	
	
	
	@CircuitBreaker(name = "listorderfallback",fallbackMethod = "getordersfallback")
	@GetMapping("/orders/{uid}")
	public ResponseEntity<List<Orders>> getOrders(@PathVariable Integer uid){
		return new ResponseEntity<List<Orders>>(resttemplate.getForObject(orderserviceurl+"/orders/"+uid, List.class),HttpStatus.OK);
	} 
	
	public ResponseEntity<Object> getordersfallback(Integer uid,Throwable t){
		System.out.println("fallback happened ");
		return new ResponseEntity<Object>("<h1>Currently order service is not working</h1>",HttpStatus.GATEWAY_TIMEOUT);
	}
	
	
	@GetMapping("/user/order/{uid}")
	public UserDto getUsersOrders(@PathVariable Integer uid){
		UserDto dto = new UserDto();
		User user = service.getUserById(uid);
		dto.setUserid(uid);
		dto.setUsername(user.getUsername());
		dto.setEmail(user.getEmail());
		dto.setAddr(user.getAddr());
		dto.setPayments(user.getPayments());
		List<Orders> orders= resttemplate.getForObject(orderserviceurl+"/orders/"+uid, List.class);
		dto.setOrders(orders);
		return dto;
		
	}
	
	
	
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
