package com.comcast.controller.app.repository;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

import com.comcast.app.repository.User;

public class UserTest {
	
	private static final Integer USERID = 1;
	private static final String USERNAME = "username1";
	private static final String FIRSTNAME = "firstName";
	private static final String LASTNAME = "lastName";
	
	@Test
	public void testGetUserId() {
		User user = new User();
		user.setId(USERID);
	}
	
	@Test
	public void testGetUserName() {
		User user = new User();
		user.setUserName(USERNAME);
		assertThat(user.getUserName()).isEqualTo(USERNAME);
	}
	@Test
	public void testSetUserName() {
		User user = new User();
		assertThat(user.getUserName()).isNull();
		user.setUserName(USERNAME);
		assertThat(user.getUserName()).isEqualTo(USERNAME);
	}
	
	@Test
	public void testGetFirstName() {
		User user = new User();
		user.setFirstName(FIRSTNAME);
		assertThat(user.getFirstName()).isEqualTo(FIRSTNAME);
	}
	@Test
	public void testSetFirstName() {
		User user = new User();
		assertThat(user.getFirstName()).isNull();
		user.setFirstName(FIRSTNAME);
		assertThat(user.getFirstName()).isEqualTo(FIRSTNAME);
	}
	@Test
	public void testGetLastName() {
		User user = new User();
		user.setLastName(LASTNAME);
		assertThat(user.getLastName()).isEqualTo(LASTNAME);
	}
	@Test
	public void testSetLastName() {
		User user = new User();
		assertThat(user.getLastName()).isNull();
		user.setLastName(LASTNAME);
		assertThat(user.getLastName()).isEqualTo(LASTNAME);
	}
	
	
}
