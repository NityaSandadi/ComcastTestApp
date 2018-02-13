package com.comcast.app.controller;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.comcast.app.controller.FibonacciNumbersController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FibonacciNumbersControllerTest {
	
	@Autowired 
	private FibonacciNumbersController fibonacciNumbersController;
	
	@Test
	public void testNullFibonacci() {
	int[] result =	this.fibonacciNumbersController.getFibonacciNumbers(null);
	Assert.assertTrue(result == null);
		
	}
	
	@Test
	public void testNFibonacci() {
		int[] result = this.fibonacciNumbersController.getFibonacciNumbers(0);
		Assert.assertTrue(result[0] == 0);
	}

	@Test
	public void testNFibonacciNumbers() {
		int[] result = this.fibonacciNumbersController.getFibonacciNumbers(15);
		Assert.assertTrue(result[15] == 610);
	}
	
}
