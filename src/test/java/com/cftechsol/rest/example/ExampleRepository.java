package com.cftechsol.rest.example;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Example repository to execute test.
 * 
 * @author Caio Frota {@literal <contact@cftechsol.com>}
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository
public interface ExampleRepository extends JpaRepository<ExampleEntity, Long> {
	
	public ExampleEntity findByName(String name);
	
}
