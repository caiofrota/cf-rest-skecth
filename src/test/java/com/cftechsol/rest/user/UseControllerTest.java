package com.cftechsol.rest.user;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.cftechsol.rest.users.User;
import com.cftechsol.rest.users.UserController;
import com.cftechsol.rest.users.UserService;

/**
 * GenericService test class.
 * 
 * @author Caio Frota <contact@cftechsol.com>
 * @version 1.0.0
 * @since 1.0.0
 */
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UseControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private UserService service;

	@Before
	public void setup() throws Exception {
		User user = new User("email@company.com", "password", "User Name");
		user.setId(1l);

		List<User> userList = new ArrayList<User>();
		userList.add(user);

		Mockito.when(service.findAll()).thenReturn(userList);
		Mockito.when(service.findById(1l)).thenReturn(user);
	}

	@Test
	public void passwordShouldntBePresentInFindAll() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/users")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("email@company.com")))
				.andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("User Name")))
				.andExpect(MockMvcResultMatchers.content().string(Matchers.not(Matchers.containsString("password"))));
	}

	@Test
	public void passwordShouldntBePresentInFindById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/users/id/1")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("email@company.com")))
				.andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("User Name")))
				.andExpect(MockMvcResultMatchers.content().string(Matchers.not(Matchers.containsString("password"))));
	}

}
