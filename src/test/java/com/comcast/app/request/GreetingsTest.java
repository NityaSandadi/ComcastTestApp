package com.comcast.app.request;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.comcast.app.request.Greetings;

public class GreetingsTest {
	
	private static final String MESSAGE = "Hello World!";
	
	@Test
	public void testGetMessage() {
		Greetings greetings = new Greetings("");
		greetings.setMessage("Hello World!");
		assertThat(greetings.getMessage()).isEqualTo(MESSAGE);
	}

	@Test
	public void testSetMessage() {
		Greetings greetings = new Greetings(null);
		assertThat(greetings.getMessage()).isNull();
		greetings.setMessage(MESSAGE);
		assertThat(greetings.getMessage()).isEqualTo(MESSAGE);
	}
}
