package com.comcast.app.request;

public class Greetings {
	private  String message;

	public Greetings(String message) {

		this.message = message;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}



}
