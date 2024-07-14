package com.training.iocdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.training.iocdemo.MyDemo;

import com.training.iocdemo.utils.DemoUtils;


@ComponentScan(basePackages = "com.training.iocdemo,org.training.iocdemo")
@SpringBootApplication
public class IocdemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ioc = SpringApplication.run(IocdemoApplication.class, args);
				
		 Demo  d = ioc.getBean(Demo.class);
		 d.getMyinterface().getMessage();
		
		 System.out.println("===================================");
		 
		 DemoService  ds = ioc.getBean(DemoService.class);
		 ds.getMyinterface().getMessage();
		
		
	}

}
