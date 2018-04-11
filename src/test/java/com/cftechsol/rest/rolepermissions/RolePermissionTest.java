package com.cftechsol.rest.rolepermissions;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cftechsol.rest.example.ExampleEntity;

/**
 * Generic entity test class.
 * 
 * @author Caio Frota <contact@cftechsol.com>
 * @version 1.0.0
 * @since 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RolePermissionTest {

	@Test
	public void shouldBeEqualsWithSameObject() throws Exception {
		ExampleEntity entity = new ExampleEntity();

		Assert.assertEquals(entity, entity);
	}

	@Test
	public void shouldBeEqualsWithSameId() throws Exception {
		RolePermission entity1 = new RolePermission();
		RolePermission entity2 = new RolePermission();
		RolePermissionPK pk = new RolePermissionPK();
		
		pk.setPermissionId(1l);
		pk.setRoleId(1l);
		entity1.setId(pk);
		entity2.setId(pk);

		Assert.assertEquals(entity1, entity2);
		Assert.assertEquals(entity1.hashCode(), entity2.hashCode());
	}
	
	@Test
	public void shouldBeDifferentNull() throws Exception {
		RolePermissionTest entity = new RolePermissionTest();
		
		Assert.assertNotEquals(entity, null);
	}
	
	@Test
	public void shouldBeDifferentWithDifferentId() throws Exception {
		RolePermission entity1 = new RolePermission();
		RolePermission entity2 = new RolePermission();
		RolePermissionPK pk1 = new RolePermissionPK();
		RolePermissionPK pk2 = new RolePermissionPK();
		
		pk1.setPermissionId(1l);
		pk1.setRoleId(1l);
		pk2.setPermissionId(2l);
		pk2.setRoleId(2l);
		
		entity1.setId(pk1);
		entity2.setId(pk2);

		Assert.assertNotEquals(entity1, entity2);
		Assert.assertNotEquals(entity1.hashCode(), entity2.hashCode());
	}
	
	@Test
	public void shouldBeDifferentOtherObject() throws Exception {
		RolePermissionTest entity = new RolePermissionTest();

		Assert.assertNotEquals(entity, new Object());
	}

}
