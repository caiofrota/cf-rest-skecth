package com.cftechsol.rest.entities;

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
public class GenericEntityTest {

	@Test
	public void shouldBeEqualsWithSameObject() throws Exception {
		ExampleEntity entity = new ExampleEntity();

		Assert.assertEquals(entity, entity);
	}

	@Test
	public void shouldBeEqualsWithSameId() throws Exception {
		ExampleEntity entity1 = new ExampleEntity();
		ExampleEntity entity2 = new ExampleEntity();
		
		entity1.setId(1l);
		entity2.setId(1l);

		Assert.assertEquals(entity1, entity2);
		Assert.assertEquals(entity1.hashCode(), entity2.hashCode());
	}
	
	@Test
	public void shouldBeDifferentNull() throws Exception {
		ExampleEntity entity = new ExampleEntity();
		
		Assert.assertNotEquals(entity, null);
	}
	
	@Test
	public void shouldBeDifferentWithDifferentId() throws Exception {
		ExampleEntity entity1 = new ExampleEntity();
		ExampleEntity entity2 = new ExampleEntity();
		
		entity1.setId(1l);
		entity2.setId(2l);

		Assert.assertNotEquals(entity1, entity2);
		Assert.assertNotEquals(entity1.hashCode(), entity2.hashCode());
	}
	
	@Test
	public void shouldBeDifferentOtherObject() throws Exception {
		ExampleEntity entity = new ExampleEntity();

		Assert.assertNotEquals(entity, new Object());
	}

}
