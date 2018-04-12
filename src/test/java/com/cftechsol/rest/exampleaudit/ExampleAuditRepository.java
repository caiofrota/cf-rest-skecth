package com.cftechsol.rest.exampleaudit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Example audit repository to execute test.
 * 
 * @author Caio Frota {@literal <contact@cftechsol.com>}
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository
public interface ExampleAuditRepository extends JpaRepository<ExampleAuditEntity, Long> {
	
}
