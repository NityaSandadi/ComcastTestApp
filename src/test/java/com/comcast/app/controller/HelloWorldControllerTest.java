package com.comcast.app.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.comcast.app.controller.HelloWorldController;
import com.comcast.app.request.Greetings;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloWorldControllerTest {
	
	@Autowired
	private HelloWorldController helloWorldController;
	
	
	private Greetings greetings = new Greetings("world");
	
	@Before
	public void setup() {
		greetings.setMessage("Hello world!");
	}
	
	
	
	@Test
	public void testSayHello() {
		greetings = this.helloWorldController.sayHello("world");
		Assert.assertTrue(this.greetings.getMessage().contentEquals("Hello world!"));
	
	}

}
