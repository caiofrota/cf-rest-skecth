package com.cftechsol.rest.exampleaudit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExampleAuditRepository extends JpaRepository<ExampleAuditEntity, Long> {
	
}
