package com.cftechsol.rest.example;

import org.springframework.stereotype.Service;

import com.cftechsol.rest.services.GenericService;

/**
 * Example service to execute test.
 * 
 * @author Caio Frota <contact@cftechsol.com>
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class ExampleService extends GenericService<ExampleRepository, ExampleEntity, Long> {
	
	public ExampleEntity findByName(String name) {
		return this.getRepository().findByName(name);
	};
	
}
