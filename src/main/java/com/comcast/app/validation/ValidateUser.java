package com.comcast.app.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.comcast.app.repository.User;
import com.comcast.app.repository.UserRepository;

@Component("validator")
public class ValidateUser {

	@Autowired
	UserRepository userRepository;

	public boolean isUserExistsByUserName(String userName) {

		User user = this.userRepository.findByUserName(userName);
		if (user != null) {
			return true;
		}
		return false;
	}

	public boolean isUserExistsById(Integer Id) {

		User user = this.userRepository.findById(Id);
		if (user != null) {
			return true;
		}
		return false;
	}

}
