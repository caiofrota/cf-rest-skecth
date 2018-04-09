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
import org.springframework.security.test.context.support.WithMockUser;
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
@WebMvcTest(controllers = UserController.class, secure = false)
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private UserService service;

	@Before
	public void setup() throws Exception {
		User user = new User("email@company.com", "password", "User Name", true, null);
		user.setId(1l);

		List<User> userList = new ArrayList<User>();
		userList.add(user);

		Mockito.when(service.findAll()).thenReturn(userList);
		Mockito.when(service.findById(1l)).thenReturn(user);
	}

	@Test
	@WithMockUser(username = "email@company.com", password = "password", roles = { "ADMIN" })
	public void passwordShouldntBePresentInFindAll() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/users").header("Origin", "*"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("email@company.com")))
				.andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("User Name")))
				.andExpect(MockMvcResultMatchers.content().string(Matchers.not(Matchers.containsString("password"))));
	}

	@Test
	@WithMockUser(username = "email@company.com", password = "password", roles = { "ADMIN" })
	public void passwordShouldntBePresentInFindById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/users/id/1").header("Origin", "*"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("email@company.com")))
				.andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("User Name")))
				.andExpect(MockMvcResultMatchers.content().string(Matchers.not(Matchers.containsString("password"))));
	}

}
