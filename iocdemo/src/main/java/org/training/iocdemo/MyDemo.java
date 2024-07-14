package org.training.iocdemo;

import org.springframework.stereotype.Component;

@Component
public class MyDemo {

	private String somemessage;

	public String getSomemessage() {
		return somemessage;
	}

	public void setSomemessage(String somemessage) {
		this.somemessage = somemessage;
	}
	
	
}
