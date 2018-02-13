package com.comcast.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.comcast.app.request.Greetings;

/*
 * Rest controller to print the hello world message.
 */
@RestController
public class HelloWorldController {

	private static final String template = "Hello %s!";

	@RequestMapping(value = "/helloworld", produces = { "application/json" }, method = RequestMethod.GET)
	public @ResponseBody Greetings sayHello(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
		return new Greetings(String.format(template, name));
	}
}
