package com.cftechsol.rest.roles;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cftechsol.rest.exceptions.NonUniqueException;

/**
 * Role service test class.
 * 
 * @author Caio Frota <contact@cftechsol.com>
 * @version 1.0.0
 * @since 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleServiceTest {
	
	@Autowired
	private RoleService service;
	
	@Test
	public void shouldCreateRole() throws Exception {
		Role example = new Role("ROLE", null, null);
		service.save(example);
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void codShouldntBeNull() throws Exception {
		Role example = new Role(null, null, null);
		service.save(example);
	}
	
	@Test(expected = NonUniqueException.class)
	public void codShouldBeUnique() throws Exception {
		Role example = new Role("UNIQUE_ROLE", null, null);
		service.save(example);
		service.save(example);
	}
	
}
