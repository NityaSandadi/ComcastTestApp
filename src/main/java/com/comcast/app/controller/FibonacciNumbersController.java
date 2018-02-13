package com.comcast.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
 * Rest controller which return the first N Fibonacci Numbers.
 */
@RestController
public class FibonacciNumbersController {

	@RequestMapping(value = "/fibonacciNumbers", produces = { "application/json" }, method = RequestMethod.GET)
	public int[] getFibonacciNumbers(@RequestParam(value = "N", required = true) Integer N) {
		if( N == null ) {
			return null;
		}
		int[] result = new int[N + 1];
		
		result[0] = 0;
		if (N == 0) {
			return result;
		}
		for (int i = 1; i <= N; i++) {
			result[i] = fibonacci(i);
		}
		return result;

	}

	public static int fibonacci(int number) {
		if (number == 1 || number == 2) {
			return 1;
		}
		return fibonacci(number - 1) + fibonacci(number - 2);
	}

}
