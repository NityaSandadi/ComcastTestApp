package com.comcast.app.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comcast.app.exception.UserNotFoundException;
import com.comcast.app.repository.User;
import com.comcast.app.repository.UserRepository;
import com.comcast.app.validation.ValidateUser;

/*
 * Rest controller which queries, adds and deletes rows.
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	@Qualifier("validator")
	private ValidateUser validateUser;

	// Get service to query all users from the User table.
	@GetMapping(value = "/")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	// Get service to queries user from the User table by id.
	@GetMapping(value = "/id/{id}")
	public User getUserById(@PathVariable("id") Integer id) throws UserNotFoundException {

		User user = userRepository.findById(id);
		if (user == null) {
			throw new UserNotFoundException();
		}
		return user;
	}
	
	// Get service to queries user from the User table by userName.
	@GetMapping(value = "/user_name/{userName}")
	public User getUserByUserName(@PathVariable("userName") String userName) throws UserNotFoundException {

		boolean userExists = this.validateUser.isUserExistsByUserName(userName);
		if (!userExists) {
			throw new UserNotFoundException();
		}

		return userRepository.findByUserName(userName);
	}
	
	// Get service to add user to the User table.
	@PostMapping(value = "/add_row")
	public ResponseEntity<?> add(@RequestBody final User user) throws UserNotFoundException, URISyntaxException {

		// checks if user with the userName already exists.
		boolean userExistsByUserName = this.validateUser.isUserExistsByUserName(user.getUserName());
		boolean userExistsById = false;
		
		Integer resultId = null;
		// checks if the user with userId already exists.
		if (user.getId() != null) {
			userExistsById = this.validateUser.isUserExistsById(user.getId());
		}
		if (userExistsByUserName || userExistsById) {
			ResponseEntity.badRequest().body("User already exists with the same userName or UserId.");
		} else {
			User result = userRepository.save(user);
			resultId = result.getId();
		}

		URI firstURI = new URI(
			      "http://localhost:8080"
			      + "/user/" +resultId);
		return ResponseEntity.created(firstURI).build();
	}
	
	// Delete service to delete user from the User table.
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") final Integer id) throws UserNotFoundException {

		boolean userExists = this.validateUser.isUserExistsById(id);
		if (!userExists) {
			throw new UserNotFoundException();
		}
		userRepository.delete(id);
		return ResponseEntity.ok().build();

	}

}
