package com.training.iocdemo;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;


@Component("impl2")
public class SampleImpl implements MyInterface{

	@Override
	public void getMessage() {
		System.out.println("Implementation from SampleImpl");
		
	}

}
