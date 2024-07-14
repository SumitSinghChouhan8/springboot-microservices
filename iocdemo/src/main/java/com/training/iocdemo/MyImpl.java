package com.training.iocdemo;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component("impl1")
public class MyImpl implements MyInterface{

	@Override
	public void getMessage() {
		System.out.println("Implentation From MyImpl");
	}

	
}
