package com.training.orderservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.training.orderservice.dao.Orders;
import com.training.orderservice.dao.OrderRepository;

@RestController
public class OrderController {

	@Autowired
	OrderRepository repo;
	

	@GetMapping("/greet")
	public String greet() {
		return "Hello form Order Service";
	}
	
	
	@GetMapping("/orders")
	public List<Orders> getAllOrders() {
		return (List<Orders>) repo.findAll();
	}
	
	@GetMapping("/order/{oid}")
	public Orders getOrder(@PathVariable Integer oid) {
		return repo.findById(oid).orElseThrow(()->new RuntimeException("Order Not Found"));
	}
	
	@GetMapping("/orders/{uid}")
	public List<Orders> getOrdersByUid(@PathVariable Integer uid){
		return repo.findByUid(uid);
	}
	
	@PostMapping("/save")
	public Orders saveOrder(@RequestBody Orders o) {
		return repo.save(o);
	}
	
	
	
}
