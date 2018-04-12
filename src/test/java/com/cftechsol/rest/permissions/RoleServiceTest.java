package com.cftechsol.rest.permissions;

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
 * @author Caio Frota {@literal <contact@cftechsol.com>}
 * @version 1.0.0
 * @since 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleServiceTest {
	
	@Autowired
	private PermissionService service;
	
	@Test
	public void shouldCreatePermission() throws Exception {
		Permission example = new Permission("PERMISSION", null);
		service.save(example);
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void codShouldntBeNull() throws Exception {
		Permission example = new Permission(null, null);
		service.save(example);
	}
	
	@Test(expected = NonUniqueException.class)
	public void codShouldBeUnique() throws Exception {
		Permission example = new Permission("UNIQUE_PERMISSION", null);
		service.save(example);
		service.save(example);
	}
	
}
