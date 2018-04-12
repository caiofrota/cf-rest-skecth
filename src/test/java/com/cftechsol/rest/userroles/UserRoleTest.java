package com.cftechsol.rest.userroles;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cftechsol.rest.example.ExampleEntity;

/**
 * User role test class.
 * 
 * @author Caio Frota <contact@cftechsol.com>
 * @version 1.0.0
 * @since 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRoleTest {

	@Test
	public void shouldBeEqualsWithSameObject() throws Exception {
		ExampleEntity entity = new ExampleEntity();

		Assert.assertEquals(entity, entity);
	}

	@Test
	public void shouldBeEqualsWithSameId() throws Exception {
		UserRole entity1 = new UserRole();
		UserRole entity2 = new UserRole();
		UserRolePK pk = new UserRolePK();
		
		pk.setRoleId(1l);
		pk.setUserId(1l);
		entity1.setId(pk);
		entity2.setId(pk);

		Assert.assertEquals(entity1, entity2);
		Assert.assertEquals(entity1.hashCode(), entity2.hashCode());
	}
	
	@Test
	public void shouldBeDifferentNull() throws Exception {
		UserRoleTest entity = new UserRoleTest();
		
		Assert.assertNotEquals(entity, null);
	}
	
	@Test
	public void shouldBeDifferentWithDifferentId() throws Exception {
		UserRole entity1 = new UserRole();
		UserRole entity2 = new UserRole();
		UserRolePK pk1 = new UserRolePK();
		UserRolePK pk2 = new UserRolePK();
		
		pk1.setRoleId(1l);
		pk1.setUserId(1l);
		pk2.setRoleId(2l);
		pk2.setUserId(2l);
		
		entity1.setId(pk1);
		entity2.setId(pk2);

		Assert.assertNotEquals(entity1, entity2);
		Assert.assertNotEquals(entity1.hashCode(), entity2.hashCode());
	}
	
	@Test
	public void shouldBeDifferentOtherObject() throws Exception {
		UserRoleTest entity = new UserRoleTest();

		Assert.assertNotEquals(entity, new Object());
	}

}
