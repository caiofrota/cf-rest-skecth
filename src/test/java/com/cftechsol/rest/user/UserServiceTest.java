package com.cftechsol.rest.user;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cftechsol.rest.exceptions.NonUniqueException;
import com.cftechsol.rest.users.User;
import com.cftechsol.rest.users.UserService;

/**
 * GenericService test class.
 * 
 * @author Caio Frota <contact@cftechsol.com>
 * @version 1.0.0
 * @since 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
	
	@Autowired
	private UserService service;
	
	@Test
	public void shouldCreateUser() throws Exception {
		User example = new User("shouldCreateUser@company.com", "Password", "User Name");
		service.save(example);
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void emailShouldntBeNull() throws Exception {
		User example = new User(null, "Password", "User Name");
		service.save(example);
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void passwordShouldntBeNull() throws Exception {
		User example = new User("emailShouldntBeNull@company.com", null, "User Name");
		service.save(example);
	}

	@Test(expected = ConstraintViolationException.class)
	public void nameShouldntBeNull() throws Exception {
		User example = new User("nameShouldntBeNull@company.com", "Password", null);
		service.save(example);
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void emailShouldBeValid() throws Exception {
		User example = new User("emailShouldBeValid", "Password", "User Name");
		service.save(example);
	}
	
	@Test(expected = NonUniqueException.class)
	public void emailShouldBeUnique() throws Exception {
		User example = new User("emailShouldBeUnique@company.com", "Password", "User Name");
		service.save(example);
		service.save(example);
	}
	
}
