package com.cftechsol.rest.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cftechsol.rest.example.ExampleEntity;
import com.cftechsol.rest.example.ExampleService;
import com.cftechsol.rest.exceptions.NonUniqueException;

/**
 * GenericService test class.
 * 
 * @author Caio Frota <contact@cftechsol.com>
 * @version 1.0.0
 * @since 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GenericServiceTest {

	@Autowired
	private ExampleService service;
	
	private String name = "Example";

	@Before
	public void setup() throws Exception {
		ExampleEntity example = new ExampleEntity(name);
		example.setId(1l);
		service.save(example);
	}
	
	@Test
	public void shouldFindAll() throws Exception {
		List<ExampleEntity> found = service.findAll();
		Assert.assertFalse(found.isEmpty());
	}

	@Test
	public void shouldFindOneById() throws Exception {
		ExampleEntity found = service.findById(1l);
		Assert.assertEquals(found.getId(), new Long(1));
	}
	
	@Test
	public void shouldUpdateOneById() throws Exception {
		String newName = "Example Changed";
		ExampleEntity found = service.findById(1l);
		found.setName(newName);
		found = service.save(found);
		Assert.assertEquals(found.getName(), newName);
		
		// Return to old status.
		found.setName(name);
		service.save(found);
	}
	
	@Test
	public void shouldSaveAuditFields() throws Exception {
		ExampleEntity example = new ExampleEntity(name + " Audit");
		example = service.save(example, 1l);
		
		Assert.assertEquals(example.getCreatedBy(), 1l);
		Assert.assertEquals(example.getUpdatedBy(), 1l);
		Assert.assertNotNull(example.getCreatedOn());
		Assert.assertNotNull(example.getUpdatedOn());
		Assert.assertEquals(example.getCreatedOn(), example.getUpdatedOn());

		// Return to old status.
		service.delete(example.getId());
	}
	
	@Test
	public void shouldUpdateAuditFields() throws Exception {
		ExampleEntity example = new ExampleEntity(name + " Audit");
		example = service.save(example, 1l);
		//Thread.sleep(1000);
		service.save(example, 2l);
		
		Assert.assertEquals(example.getCreatedBy(), 1l);
		Assert.assertEquals(example.getUpdatedBy(), 2l);
		Assert.assertNotNull(example.getCreatedOn());
		Assert.assertNotNull(example.getUpdatedOn());
		Assert.assertNotEquals(example.getCreatedOn(), example.getUpdatedOn());

		// Return to old status.
		service.delete(example.getId());
	}

	@Test
	public void whenValidNameEntityShouldBeFound() {
		String name = "Example";
		ExampleEntity found = service.findByName(name);
		Assert.assertEquals(found.getName(), name);
	}
	
	@Test(expected = NoSuchElementException.class)
	public void shouldDeleteById() throws Exception {
		service.delete(1l);
		service.findById(1l);
	}
	
	@Test(expected = NonUniqueException.class)
	public void shouldDeleteByIds() throws Exception {
		throw new NonUniqueException("Teste", new String[] { "keys" }, new String[] { "values" });
	}

}
