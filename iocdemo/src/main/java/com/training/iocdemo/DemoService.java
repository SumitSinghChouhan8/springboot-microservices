package com.training.iocdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DemoService {

	@Autowired
	private MyInterface myinterface;
	
	
	public MyInterface getMyinterface() {
		return myinterface;
	}

	public void setMyinterface(MyInterface myinterface) {
		this.myinterface = myinterface;
	}

	private int servid;

	public int getServid() {
		return servid;
	}

	public void setServid(int servid) {
		this.servid = servid;
	}
	
	
	
	
}
