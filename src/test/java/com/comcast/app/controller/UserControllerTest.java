package com.comcast.app.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.comcast.app.repository.User;
import com.comcast.app.repository.UserRepository;
import com.comcast.app.validation.ValidateUser;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest
@ContextConfiguration
public class UserControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	@Qualifier("validator")
	private ValidateUser validateUser;

	@MockBean
	private UserRepository userRepository;

	@Autowired
	private WebApplicationContext webApplicationContext;

	List<User> userList = new ArrayList<User>();

	@Before
	public void setup() {

		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		this.userRepository.deleteAllInBatch();

		this.userList.add(new User(1, "user1", "firstName", "lastName"));
		this.userList.add(new User(2, "user2", "firstName", "lastName"));

	}

	@Test
	public void testGetAllUsers() throws Exception {

		Mockito.when(this.userRepository.findAll()).thenReturn(this.userList);

		mockMvc.perform(get("/user/").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id", is(this.userList.get(0).getId().intValue())))
				.andExpect(jsonPath("$[0].userName", is(this.userList.get(0).getUserName())))
				.andExpect(jsonPath("$[0].firstName", is(this.userList.get(0).getFirstName())))
				.andExpect(jsonPath("$[0].lastName", is(this.userList.get(0).getLastName())))
				.andExpect(jsonPath("$[1].id", is(this.userList.get(1).getId().intValue())))
				.andExpect(jsonPath("$[1].userName", is(this.userList.get(1).getUserName())))
				.andExpect(jsonPath("$[1].firstName", is(this.userList.get(1).getFirstName())))
				.andExpect(jsonPath("$[1].lastName", is(this.userList.get(1).getLastName())));

		Mockito.verify(userRepository).findAll();

	}

	@Test
	public void testGetUserById() throws Exception {

		Mockito.when(this.userRepository.findById(this.userList.get(0).getId())).thenReturn(this.userList.get(0));

		mockMvc.perform(get("/user/id/" + this.userList.get(0).getId()).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.id", is(this.userList.get(0).getId().intValue())))
				.andExpect(jsonPath("$.userName", is(this.userList.get(0).getUserName())))
				.andExpect(jsonPath("$.firstName", is(this.userList.get(0).getFirstName())))
				.andExpect(jsonPath("$.lastName", is(this.userList.get(0).getLastName())));

		Mockito.verify(userRepository).findById(this.userList.get(0).getId());

	}

	@Test
	public void testGetUserByUserName() throws Exception {

		Mockito.when(this.userRepository.findByUserName(this.userList.get(1).getUserName()))
				.thenReturn(this.userList.get(1));
		Mockito.when(this.validateUser.isUserExistsByUserName(this.userList.get(1).getUserName())).thenReturn(true);

		mockMvc.perform(get("/user/user_name/" + this.userList.get(1).getUserName()).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.id", is(this.userList.get(1).getId().intValue())))
				.andExpect(jsonPath("$.userName", is(this.userList.get(1).getUserName())))
				.andExpect(jsonPath("$.firstName", is(this.userList.get(1).getFirstName())))
				.andExpect(jsonPath("$.lastName", is(this.userList.get(1).getLastName())));

		Mockito.verify(userRepository).findByUserName(this.userList.get(1).getUserName());

	}

	@Test
	public void testaddRow() throws Exception {
		Mockito.when(this.userRepository.save(any(User.class))).thenReturn(this.userList.get(0));
		Mockito.when(this.validateUser.isUserExistsByUserName(this.userList.get(0).getUserName())).thenReturn(false);
		Mockito.when(this.validateUser.isUserExistsById(this.userList.get(0).getId())).thenReturn(false);

		ObjectMapper mapper = new ObjectMapper();

		// Object to JSON in String
		String jsonInString = mapper.writeValueAsString(this.userList.get(0));

		mockMvc.perform(post("/user/add_row").contentType(MediaType.APPLICATION_JSON).content(jsonInString))
				.andExpect(status().isCreated());

	}

}
