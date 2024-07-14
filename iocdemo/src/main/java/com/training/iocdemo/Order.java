package com.training.iocdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Order {

	private Integer oid;
	
	@Autowired private Product product;
	
	@Autowired
	DemoService service;
	
	Order(){
		
	}
	
	Order(Product product){
		this.product = product;
	}
	
	Order(DemoService service){
		this.service = service;
	}
	
	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public Product getProduct() {
		return product;
	}

	
	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Order [oid=" + oid + ", product=" + product + ", service=" + service + "]";
	}
	
	
	
	
}
