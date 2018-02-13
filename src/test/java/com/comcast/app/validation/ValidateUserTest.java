package com.comcast.app.validation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.comcast.app.repository.User;
import com.comcast.app.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ValidateUserTest {
	
	@Autowired
	private ValidateUser validateUser;
	
	@MockBean
	UserRepository userRepository;
		
	private User user;
	
	@Before
	public void setup() {
		 user = new User(1, "user1", "ABC","EFG");
	}
	
	
	@Test
	public void isUserExistsByUserNameTest() {
		Mockito.when(this.userRepository.findByUserName("user2")).thenReturn(user);
		Assert.assertTrue(this.validateUser.isUserExistsByUserName("user2") == true);
		
	}
	
	@Test
	public void isUserExistsByUserNameNegativeTest() {
		Mockito.when(this.userRepository.findByUserName("user1")).thenReturn(user);
		Assert.assertTrue(this.validateUser.isUserExistsByUserName("user2") == false);
		
	}

	@Test
	public void isUserExistsByIdTest() {
		Mockito.when(this.userRepository.findById(1)).thenReturn(user);
		Assert.assertTrue(this.validateUser.isUserExistsById(2) == false);
		
	}
	
	@Test
	public void isUserExistsByIdNegativeTest() {
		Mockito.when(this.userRepository.findById(2)).thenReturn(user);
		Assert.assertTrue(this.validateUser.isUserExistsById(2) == true);
		
	}
}
