package com.training.iocdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;



@Component
public class Demo {

	@Qualifier("impl2")
	@Autowired
	MyInterface myinterface;

	public MyInterface getMyinterface() {		
		return myinterface;
	}

	public void setMyinterface(MyInterface myinterface) {
		this.myinterface = myinterface;
	}
	
}
