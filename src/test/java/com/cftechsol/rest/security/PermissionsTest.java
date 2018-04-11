package com.cftechsol.rest.security;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.cftechsol.rest.example.ExampleEntity;
import com.cftechsol.rest.example.ExampleSecurityController;
import com.cftechsol.rest.example.ExampleService;

/**
 * Authentication test class.
 * 
 * @author Caio Frota <contact@cftechsol.com>
 * @version 1.0.0
 * @since 1.0.0
 */
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ExampleSecurityController.class)
public class PermissionsTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private ExampleService service;
	
	ExampleEntity example = new ExampleEntity("Example");
	
	@Before
	public void setup() throws Exception {
		example.setId(1l);

		List<ExampleEntity> exampleList = new ArrayList<ExampleEntity>();
		exampleList.add(example);

		Mockito.when(service.findAll()).thenReturn(exampleList);
		Mockito.when(service.findById(1l)).thenReturn(example);
	}

	@Test
	@WithMockUser(username = "usernoaccess@company.com", password = "password", authorities = {})
	public void shouldGetForbiddenWithoutAuthorities() throws Exception {
		// @formatter:off
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/example").header("Origin", "*"))
				.andExpect(MockMvcResultMatchers.status().isForbidden());
		// @formatter:on
	}
	
	@Test
	@WithMockUser(username = "otherauthority@company.com", password = "password", authorities = { "OTHER_AUTHORITY" })
	public void shouldGetAccessWithDifferentAuthorities() throws Exception {
		// @formatter:off
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/example")
						.header("Origin", "*"))
				.andExpect(MockMvcResultMatchers.status().isForbidden());
		// @formatter:on
	}

	@Test
	@WithMockUser(username = "user@company.com", password = "password", authorities = { "EXAMPLE_FIND_ALL" })
	public void shouldGetAccessToFindAllWithFindAllAuthorities() throws Exception {
		// @formatter:off
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/example")
						.header("Origin", "*"))
				.andExpect(MockMvcResultMatchers.status().isOk());
		// @formatter:on
	}
	
	@Test
	@WithMockUser(username = "user@company.com", password = "password", authorities = { "EXAMPLE_FIND_ALL", "EXAMPLE_SAVE" })
	public void shouldGetAccessToSaveWithAllAuthorities() throws Exception {
		// @formatter:off
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/example/allroles")
						.header("Origin", "*")
						.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.status().isOk());
		// @formatter:on
	}
	
	@Test
	@WithMockUser(username = "user@company.com", password = "password", authorities = { "EXAMPLE_FIND_ALL" })
	public void shouldGetForbiddenToSaveWithSomeAuthorities() throws Exception {
		// @formatter:off
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/example/allroles")
						.header("Origin", "*")
						.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.status().isForbidden());
		// @formatter:on
	}

}
