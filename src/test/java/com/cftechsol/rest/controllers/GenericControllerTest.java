package com.cftechsol.rest.controllers;

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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.cftechsol.rest.example.ExampleController;
import com.cftechsol.rest.example.ExampleEntity;
import com.cftechsol.rest.example.ExampleService;
import com.google.gson.Gson;

/**
 * GenericController test class.
 * 
 * @author Caio Frota {@literal <contact@cftechsol.com>}
 * @version 1.0.0
 * @since 1.0.0
 */
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ExampleController.class, secure = false)
public class GenericControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private ExampleService service;

	private String name = "Example";
	private String newName = "Example Chaged";

	ExampleEntity example = new ExampleEntity(name);
	ExampleEntity exampleUpdate = new ExampleEntity(newName);

	@Before
	public void setup() throws Exception {
		example.setId(1l);
		exampleUpdate.setId(1l);

		List<ExampleEntity> exampleList = new ArrayList<ExampleEntity>();
		exampleList.add(example);

		Mockito.when(service.findAll()).thenReturn(exampleList);
		Mockito.when(service.findById(1l)).thenReturn(example);
		Mockito.when(service.findByName(name)).thenReturn(example);
		Mockito.when(service.save(exampleUpdate)).thenReturn(exampleUpdate);
	}

	@Test
	public void shouldFindAll() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/example").header("Origin", "*"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(Matchers.containsString(name)));
	}

	@Test
	public void shouldFindOneById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/example/id/1").header("Origin", "*"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(Matchers.containsString(name)));
	}

	@Test
	public void shouldUpdateOneById() throws Exception {
		Gson gson = new Gson();
		mockMvc.perform(MockMvcRequestBuilders.post("/example").header("Origin", "*")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(gson.toJson(exampleUpdate)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(Matchers.containsString(newName)));
	}

	@Test
	public void whenValidNameEntityShouldBeFound() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/example/filter?name=" + name).header("Origin", "*"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(Matchers.containsString(name)));
	}

	@Test
	public void shouldDeleteById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/example?id=1").header("Origin", "*"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("")));
	}

	@Test
	public void shouldGetException() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/example/exception").header("Origin", "*"))
				.andExpect(MockMvcResultMatchers.status().isInternalServerError())
				.andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("Example Message")));
	}

	@Test
	public void shouldGetNonUniqueException() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/example/nonUniqueException").header("Origin", "*"))
				.andExpect(MockMvcResultMatchers.status().isBadRequest())
				.andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("keys")));
	}

	@Test
	public void shouldGetValidationException() throws Exception {
		Gson gson = new Gson();
		ExampleEntity validationExample = new ExampleEntity();
		mockMvc.perform(MockMvcRequestBuilders.post("/example/validationException").header("Origin", "*")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(gson.toJson(validationExample)))
				.andExpect(MockMvcResultMatchers.status().isBadRequest())
				.andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("name")));
	}

}
