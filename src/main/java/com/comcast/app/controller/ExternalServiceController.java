package com.comcast.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/*
 * Rest controller which calls another Rest service.
 */
@RestController
public class ExternalServiceController {
	private static final String getURL = "https://jsonplaceholder.typicode.com/posts";

	@RequestMapping(value = "/externalService", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<String> getService() {

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<String> response = restTemplate.getForEntity(getURL, String.class);
		return response;

	}

}
